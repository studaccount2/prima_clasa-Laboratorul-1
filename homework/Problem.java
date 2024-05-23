package homework;

import compulsory.Entity;
import compulsory.Project;
import compulsory.Student;
import lombok.Getter;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.graph4j.util.VertexSet;

import java.util.*;

public class Problem {

    private static final Logger logger = LogManager.getLogger(Problem.class);

    List<Project> projects;
    List<Student> students;
    @Getter
    Map<Student, Set<Project>> admissibleProjects;

    public Problem() {
        this.projects = new ArrayList<>();
        this.students = new ArrayList<>();
        this.admissibleProjects = new HashMap<>();
    }

    public List<Entity> getAllEntities() {
        List<Entity> allEntities = new ArrayList<>();
        allEntities.addAll(projects);
        allEntities.addAll(students);
        return allEntities;
    }

    public List<Project> getProjects() {
        return projects == null ? null : Collections.unmodifiableList(projects);
    }

    public List<Student> getStudents() {
        return students == null ? null : Collections.unmodifiableList(students);
    }

    public int getAllEntitiesSize() {
        return projects.size() + students.size();
    }

    public void addProject(@NonNull Project project) {
        if (projects.contains(project)) {
            logger.warn("The project: " + project + " already exists in this problem instance!");
            return;
        }
        projects.add(project);
    }

    public void addStudent(@NonNull Student student) {
        if (students.contains(student)) {
            logger.warn("The student: " + student + " already exists in this problem instance!");
            return;
        }
        students.add(student);
    }

    public void addAdmissibleProject(@NonNull Student student, @NonNull Project project) {
        if (!projects.contains(project)) {
            logger.warn("The project: " + project + " doesn't exist in this problem instance!");
            return;
        }
        if (!students.contains(student)) {
            logger.warn("The student: " + student + " doesn't exist in this problem instance!");
            return;
        }
        Set<Project> projects = admissibleProjects.get(student);
        if (projects != null) {
            admissibleProjects.get(student).add(project);
        } else {
            admissibleProjects.put(student, new HashSet<>() {{
                add(project);
            }});
        }
    }

    public Project getProject(int index) {
        validateIndex(index);
        return projects.get(index);
    }

    public int getProjectSize() {
        return projects.size();
    }

    /**
     * This method first, computes the average number of admissible projects, then uses that to get all the students that have a lower number of admissible projects.
     *
     * @return
     */
    public List<Student> getStudentsWithLowerPreferences() {
        double avereage = admissibleProjects
                .values()
                .stream()
                .mapToInt(Set::size)
                .average()
                .orElse(0.0);

        return admissibleProjects
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() < avereage)
                .map(Map.Entry::getKey).toList();
    }

    private void validateIndex(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than 0!");
    }

    public Entity getEntityByIndex(int index) {
        validateIndex(index);
        Project project = projects.stream().filter(entity -> entity.getIndex() == index).findFirst().orElse(null);
        if (project != null) {
            return project;
        }
        return students.stream().filter(entity -> entity.getIndex() == index).findFirst().orElse(null);
    }

    public Set<Entity> getEntitiesFromIndexes(@NonNull VertexSet vertexSet) {
        Set<Entity> entities = new HashSet<>();
        vertexSet.forEach(vertex -> entities.add(getEntityByIndex(vertex)));
        return entities;
    }

}
