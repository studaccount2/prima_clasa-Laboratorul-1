package homework;

import compulsory.Project;
import compulsory.Student;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ProblemSolver {
   private final Problem problem;

    @Getter
    Map<Project, Student> solution;

    public ProblemSolver(@NonNull Problem problem) {
        this.problem = problem;
        solution = new HashMap<>();
    }

    public void solve() {
        for (Student student : problem.admissibleProjects.keySet()) {
            for (Project project : problem.admissibleProjects.get(student)) {
                if (!solution.containsKey(project)) {
                    solution.put(project, student);
                    break;
                }
            }
        }
    }

}
