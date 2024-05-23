package bonus;

import com.github.javafaker.Faker;
import compulsory.Project;
import compulsory.Student;
import homework.Problem;
import lombok.Getter;
import lombok.NonNull;

import java.util.*;
import java.util.stream.IntStream;

public class InstanceGenerator {
    @Getter
    private final Problem problem;
    private final Random rand;

    /**
     * This methods generates a problem with noProjects projects and noStudents students.
     *
     * @param noProjects
     * @param noStudents
     */
    public InstanceGenerator(int noProjects, int noStudents) {

        problem = new Problem();
        rand = new Random();
        Faker faker = new Faker();
        LinkedList<Student> students = new LinkedList<>(IntStream.rangeClosed(0, noStudents - 1)
                .mapToObj(i -> new Student(faker.name().fullName()))
                .toList());

        LinkedList<Project> projects = new LinkedList<>(IntStream.rangeClosed(0, noProjects - 1)
                .mapToObj(i -> new Project(faker.job().title()))
                .toList());

        students.forEach(problem::addStudent);
        projects.forEach(problem::addProject);
        students.forEach(this::assignProjectsToStudent);

    }

    private List<Integer> getRandomNumbers(int size) {
        List<Integer> a = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            a.add(i);
        }
        Collections.shuffle(a);
        return a;
    }

    /**
     * This method generates a random List of Integers in range 0 to noProjects. Then, based on that orders it assigns for the current student the projects.
     *
     * @param student
     */
    private void assignProjectsToStudent(@NonNull Student student) {
        int noOfProjects = rand.nextInt(problem.getProjectSize());
        List<Integer> projectIds = getRandomNumbers(noOfProjects);
        for (Integer a : projectIds) {
            problem.addAdmissibleProject(student, problem.getProject(a));
        }
    }
}
