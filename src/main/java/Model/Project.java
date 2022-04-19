package Model;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * objects of this class holds the outline for a project.
 */
public class Project {
    private String projectName;
    private LocalDate deadline;
    private String description;
    private HashMap<User, Boolean> assignedUser;
    private Board board;
    private ProjectManager manager;
    private final static int projectID = 0;


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

    public HashMap<User, Boolean> getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(HashMap<User, Boolean> assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ProjectManager getManager() {
        return manager;
    }

    public void setManager(ProjectManager manager) {
        this.manager = manager;
    }

    public void setName(String newName) {
    }

    public HashMap<User, Boolean> getAssignedUsers() {
        return assignedUser;
    }
    //TODO lägg till i assigned users
    public void setAssignedUsers(HashMap<User, Boolean> assignees) {

    }

    /**
     * builder-class for Project
     */
    public static class ProjectBuilder{
        private final Project project = new Project();
        public Project build(){
            return project;
        }
        public ProjectBuilder projectName(String projectName) {
            project.setProjectName(projectName);
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
            project.getAssignedUser().put(admin, true);
            return this;
        }

    }

}
