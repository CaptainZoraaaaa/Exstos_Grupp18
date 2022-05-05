package controller;

import Model.*;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private Task task;
    private User user;
    private ArrayList<Project> projects = new ArrayList<>();
    private TaskManager taskManager;
    private UserManager userManager = new UserManager();
    private ProjectManager projectManager;
    private ServerStub serverStub = new ServerStub();
    private static Controller controller = new Controller();

    public static Controller getInstance(){
        return controller;
    }

    public void createNewProject(String name, String description, LocalDate deadline, User userAdmin) {
        Project project = projectManager.createProject(name, description, deadline, userAdmin);
        projects.add(project);
    }

    public void editProject () {
    }

    public void listProjects () {

    }

    /**
     * @param username the chosen username
     * @param password the chosen password
     * @param profilePicture
     */
    public boolean registerNewUser (String username, String password, Image profilePicture) {
        if(username != null && password != null && checkUsername(username)) {
            user = userManager.createNewUser(username, password, profilePicture);
            return true;
        }
        else {
            return false;
        }
        //todo felmeddelande annars? ÄNDRADE TILL BOOLEAN
    }

    public boolean checkUsername (String username) { //todo ändrat parametrar
        boolean uniqueUsername = serverStub.checkUsername(username);
        return uniqueUsername;
    }

    public void displayMyPages () {
    }

    public void displayCalender () {
    }

    public boolean logIn (String username, String password) {
        boolean login = serverStub.loginCheck(username, password);
        return login;
    }

    public void logOut () {
    }

    public void createTask () {
    }

    public void editTask () {
    }

    public void assignToTask () {
    }

    public void commentTask () {
    }

    public void editCommentOnTask () {
    }

    public void flagForHelp () {
    }

    public void deFlagHelp () {
    }

    public void showFlaggedTasks () {
    }

    public void archiveTasks () {
    }

    public void deleteTask () {
    }

    public void retrieveTask () {
    }

    public void sendTaskDetails () {
    }

    public void sendProjectDetails () {
    }

    public void changeSwimlaneTaskLimit (Swimlane swimlane, Task task) {
    }

    public void sendStatistics () {
    }

    public void sendCalender () {
    }

    public void changeProject (int projectID) {

    }

    public void operation () {
    }
}
