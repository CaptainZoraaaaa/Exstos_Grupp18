package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * objects of this class holds the outline for a project.
 *
 * @author Emma Mörk.
 */
public class Project implements Serializable {

    private String projectName;
    private LocalDate deadline;
    private LocalDate createdDate;
    private String description;
    private HashMap<String, Boolean> assignedUsers;
    private ArrayList<Task> taskList = new ArrayList<>();
    private ProjectManager projectManager;
    private int projectID;
    private int maxTasksInProgress; //// TODO: 2022-05-20  Ta bort? 
    private int maxTasksWaiting; //// TODO: 2022-05-20 Ta bort?


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Boolean> getAssignedUser() { /// TODO: 2022-05-20 Denna ska tas bort, använda enbart getAssignedUsers()
        return assignedUsers;
    }

    public void setAssignedUser(HashMap<String, Boolean> assignedUsers) { /// TODO: 2022-05-20  Denna ska tas bort, använda enbart setAssignedUsers()
        this.assignedUsers = assignedUsers;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void setName(String newName) { /// TODO: 2022-05-20 Kolla med Emma om denna ska användas?

    }

    public HashMap<String, Boolean> getAssignedUsers() {
        return assignedUsers;
    }

    //TODO lägg till i assigned users
    public void setAssignedUsers(HashMap<String, Boolean> assignees) {

    }

    public int getProjectID() {
        return projectID;
    }

    public void addNewTask(Task task) {
        taskList.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * builder-class for Project
     */
    public static class ProjectBuilder {

        private final Project project = new Project();

        public Project build() {
            return project;
        }

        public ProjectBuilder projectName(String projectName) {
            project.setProjectName(projectName);
            return this;
        }

        public ProjectBuilder assignedUsers(HashMap<String, Boolean> assignees) {
            project.setAssignedUser(assignees);
            return this;
        }

        public ProjectBuilder createdDate (LocalDate createdDate) {
            project.setCreatedDate(createdDate);
            return this;
        }

        public ProjectBuilder deadline(LocalDate deadline) {
            project.setDeadline(deadline);
            return this;
        }

        public ProjectBuilder description(String description) {
            project.setDescription(description);
            return this;
        }

        public ProjectBuilder userAdmin(User admin) {
            project.getAssignedUser().put(admin.getUsername(), true);
            return this;
        }

    }

}
