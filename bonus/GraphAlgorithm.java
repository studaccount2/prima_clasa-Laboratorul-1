package bonus;


import compulsory.Entity;
import homework.Problem;
import lombok.NonNull;

import java.util.Set;

/**
 * Abstract class with methods that define the operations that need to be done on the given problem instance.
 */
public abstract class GraphAlgorithm {
    protected Problem problem;

    public GraphAlgorithm(@NonNull Problem problem) {
        this.problem = problem;
    }

    public abstract Set<EdgeData> getMaxCardinalityMatching();

    public abstract Set<Entity> getMinVertexCover();

    public abstract Set<Entity> getMaxStableSet();

}
