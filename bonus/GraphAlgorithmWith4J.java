package bonus;

import compulsory.Entity;
import compulsory.Project;
import compulsory.Student;
import homework.Problem;
import lombok.NonNull;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GraphAlgorithmWith4J extends GraphAlgorithm {
    private final HopcroftKarpMaximumMatching matching;

    public GraphAlgorithmWith4J(@NonNull Problem problem) {
        super(problem);
        matching = new HopcroftKarpMaximumMatching(convertProblemToGraph(problem));
        this.problem = problem;
    }

    /**
     * This method gets the matching and then creates a Set of {@link EdgeData} objects which represents the maximum cardinality matching.
     *
     * @return
     */
    @Override
    public Set<EdgeData> getMaxCardinalityMatching() {
        Set<EdgeData> edges = new HashSet<>();
        Arrays.stream(matching.getMatching().edges()).toList().forEach(edge -> edges.add(
                new EdgeData(problem.getEntityByIndex(edge[0]), problem.getEntityByIndex(edge[1]))));
        return edges;
    }


    @Override
    public Set<Entity> getMinVertexCover() {
        return problem.getEntitiesFromIndexes(matching.getMinimumVertexCover());
    }

    @Override
    public Set<Entity> getMaxStableSet() {
        return problem.getEntitiesFromIndexes(matching.getMaximumStableSet());
    }

    /**
     * This method converts the input {@link Problem} into a Graph from Graph4J library. First, it adds all the entities from the given problem, then adds the edges.
     *
     * @param problem
     * @return
     */
    public Graph<Entity, Object> convertProblemToGraph(@NonNull Problem problem) {
        Graph graph = GraphBuilder.empty().estimatedNumVertices(problem.getAllEntitiesSize()).buildGraph();

        problem.getAllEntities().forEach(entity -> graph.addVertex(entity.getIndex(), entity));

        for (Student student : problem.getAdmissibleProjects().keySet()) {
            for (Project project : problem.getAdmissibleProjects().get(student)) {
                graph.addEdge(student, project);
            }
        }
        return graph;
    }

}

