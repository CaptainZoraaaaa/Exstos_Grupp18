package Sandbox;

import Model.*;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestController {
    private Task task;
    private User user;
    private ArrayList<Project> projects = new ArrayList<>();
    private TaskManager taskManager;
    private UserManager userManager = new UserManager();
    private ProjectManager projectManager;
    private ServerStub serverStub = new ServerStub();

    public void createNewProject(String name, String description, LocalDate deadline, String user, String creator) {
        System.out.println(name);
        System.out.println(description);
        System.out.println(deadline.toString());
        System.out.println(user);
        System.out.println(creator);
    }

    public void editProject () {
    }

    public void listProjects () {

    }

    public boolean registerNewUser (String username, String password, Image profilePicture) {
        return false;
    }

    public boolean checkUsername () {
        return false;
    }

    public void displayMyPages () {
    }

    public void displayCalender () {
    }

    public boolean logIn () {
        return false;
    }

    public void logOut () {
    }

    public void createTask (String header, String description, String deadline, String user, String status, String creator, String comment, boolean flagged ) {
        System.out.println(header);
        System.out.println(description);
        System.out.println(deadline);
        System.out.println(user);
        System.out.println(status);
        System.out.println(creator);
        System.out.println(comment);
        System.out.println(flagged);
    }

    public void editTask (String header, String description, String deadline, String user, String status, String creator, String comment, boolean flagged) {
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

    public void changeSwimlaneTaskLimit () {
    }

    public void sendStatistics () {
    }

    public void sendCalender () {
    }

    public void changeProject () {

    }
    public void operation () {
    }
}

