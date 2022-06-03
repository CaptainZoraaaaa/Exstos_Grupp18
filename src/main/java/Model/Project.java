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

    public void setName(String newName) { /// TODO: 2022-05-20 Kolla med Emma om denna ska användas?

    }

    public HashMap<String, Boolean> getAssignedUsers() {
        return assignedUsers;
    }

    public int getProjectID() {
        return projectID;
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
    public Task setNewTaskStatus(int id, Swimlane status){
        System.out.println(id);
        System.out.println(status);
        System.out.println("vi kom hit");
        Task taskBack = null;
        for (Task task : taskList) {
            if (task.getTask_id() == id) {
                if (task.getCurrentStatus() != status) {
                    task.setCurrentStatus(status);
                    taskBack = task;
                    System.out.println(taskBack.getHeader()+"taskback i project");
                    System.out.println(task.getHeader()+"task i project");
                }
            }
        }

        return taskBack;
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
