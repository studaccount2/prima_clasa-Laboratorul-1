package bonus;

import compulsory.Entity;
import compulsory.Project;
import compulsory.Student;
import homework.Problem;
import lombok.NonNull;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represent a concrete implementation of {@link GraphAlgorithm}. It uses the JGraphT library to implement the abstract methods.
 */
public class GraphAlgorithmWithGraphT extends GraphAlgorithm {
    Graph<Entity, DefaultEdge> graph;

    public GraphAlgorithmWithGraphT(@NonNull Problem problem) {
        super(problem);
        graph = convertProblemToGraph(problem);
    }

    /**
     * This method uses {@link HopcroftKarpMaximumCardinalityBipartiteMatching} in order to get the maximum cardinality matching.
     * It maps the edges of the matching to a Set of {@link EdgeData} objects using the methiods: getEdgeSource and getEdgeTarget of the {@link Graph}.
     *
     * @return
     */
    @Override
    public Set<EdgeData> getMaxCardinalityMatching() {
        HopcroftKarpMaximumCardinalityBipartiteMatching<Entity, DefaultEdge> matching = new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph,
                new HashSet<>(problem.getStudents()),
                new HashSet<>(problem.getProjects()));
        return matching
                .getMatching()
                .getEdges()
                .stream()
                .map(edge -> new EdgeData(graph.getEdgeSource(edge), graph.getEdgeTarget(edge)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Entity> getMinVertexCover() {
        VertexCoverAlgorithm<Entity> vertexCoverAlgorithm = new RecursiveExactVCImpl<>(graph);
        return vertexCoverAlgorithm.getVertexCover();
    }

    /**
     * This method computes the maximum stable set using the fact the complement set of vertices of a minimum vertex cover is a maximum stable set.
     * So, it only computes the difference between the initial set of vertices and the vertices that make the minimum vertex cover.
     *
     * @return
     */
    @Override
    public Set<Entity> getMaxStableSet() {
        VertexCoverAlgorithm<Entity> vertexCoverAlgorithm = new RecursiveExactVCImpl<>(graph);
        Set<Entity> entities = new HashSet<>(graph.vertexSet());
        vertexCoverAlgorithm.getVertexCover().stream().toList().forEach(entities::remove);
        return entities;
    }

    /**
     * This method converts the input {@link Problem} into a Graph from Graph4J library. First, it adds all the entities from the given problem, then adds the edges.
     *
     * @param problem
     * @return
     */
    public Graph<Entity, DefaultEdge> convertProblemToGraph(@NonNull Problem problem) {
        Graph<Entity, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        problem.getAllEntities().forEach(graph::addVertex);
        for (Student student : problem.getAdmissibleProjects().keySet()) {
            for (Project project : problem.getAdmissibleProjects().get(student)) {
                graph.addEdge(student, project);
            }
        }
        return graph;
    }

}
