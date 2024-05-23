package bonus;

import compulsory.Entity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import java.util.Set;

@RequiredArgsConstructor
public class InstanceSolver {
    private static final Logger logger = LogManager.getLogger(InstanceSolver.class);

    long initialTime;
    @NonNull
    @Required
    private InstanceGenerator instanceGenerator;

    public void solveStableMatching() {
        logger.info("Stable matching:");
        logger.info("Results for Graph4J");
        startTimer();
        GraphAlgorithm graphAlgorithmWith4J = new GraphAlgorithmWith4J(instanceGenerator.getProblem());
        Set<EdgeData> edgeDataSet = graphAlgorithmWith4J.getMaxCardinalityMatching();
        logger.info("size = " + edgeDataSet.size());
        logger.info(edgeDataSet);
        printElapsedTime();

        logger.info("Results for JGraphT");
        GraphAlgorithm graphAlgorithmWithGraphT = new GraphAlgorithmWithGraphT(instanceGenerator.getProblem());
        edgeDataSet = graphAlgorithmWithGraphT.getMaxCardinalityMatching();
        logger.info("size = " + edgeDataSet.size());
        logger.info(edgeDataSet);
        printElapsedTime();
    }

    public void solveMinVertexCover() {
        logger.info("Vertex Cover:");
        logger.info("Results for Graph4J");

        startTimer();
        GraphAlgorithm graphAlgorithmWith4J = new GraphAlgorithmWith4J(instanceGenerator.getProblem());
        Set<Entity> edgeDataSet = graphAlgorithmWith4J.getMinVertexCover();
        logger.info("size = " + edgeDataSet.size());
        System.out.println(edgeDataSet);
        printElapsedTime();

        logger.info("Results for JGraphT");
        GraphAlgorithm graphAlgorithmWithGraphT = new GraphAlgorithmWithGraphT(instanceGenerator.getProblem());
        edgeDataSet = graphAlgorithmWithGraphT.getMinVertexCover();
        logger.info("size = " + edgeDataSet.size());
        logger.info(edgeDataSet);
        printElapsedTime();
    }

    public void solveMaxStableSet() {
        logger.info("Stable Set:");
        logger.info("Results for Graph4J");

        startTimer();
        GraphAlgorithm graphAlgorithmWith4J = new GraphAlgorithmWith4J(instanceGenerator.getProblem());
        Set<Entity> edgeDataSet = graphAlgorithmWith4J.getMaxStableSet();
        logger.info("size = " + edgeDataSet.size());
        logger.info(edgeDataSet);
        printElapsedTime();

        logger.info("Results for JGraphT");
        GraphAlgorithm graphAlgorithmWithGraphT = new GraphAlgorithmWithGraphT(instanceGenerator.getProblem());
        edgeDataSet = graphAlgorithmWithGraphT.getMaxStableSet();
        logger.info("size = " + edgeDataSet.size());
        logger.info(edgeDataSet);
        printElapsedTime();
    }

    private void startTimer() {
        initialTime = System.currentTimeMillis();
    }

    private void printElapsedTime() {
        logger.info("Elapsed time: " + (System.currentTimeMillis() - initialTime) + " ms");
    }
}
