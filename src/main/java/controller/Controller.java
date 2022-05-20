package controller;

import Model.*;
import Model.Package;
import client.Client;
import client.ClientBuffer;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Max Tiderman & Anna Håkansson
 */
public class Controller {
    private Task task;
    private User user;
    private Client client;
    private ArrayList<Project> projects = new ArrayList<>();
    private Project project;
    private TaskManager taskManager;
    private UserManager userManager = new UserManager();
    private ProjectManager projectManager;
    private ServerStub serverStub = new ServerStub();
    private static Controller controller = new Controller();
    private static ClientBuffer clientBuffer = new ClientBuffer();

    public Controller() {
       // client = new Client(null, "localhost", 8080);
    }

    public static Controller getInstance(){
        return controller;
    }

    public void createNewProject(String name, String description, LocalDate deadline, User userAdmin) {
        Project project = projectManager.createProject(name, description, deadline, userAdmin);
        projects.add(project);
    }

    public void editProject () {
    }

    public boolean registerOnServer(String username, String password) {
        boolean OK = false;
        User user = user = new UserManager().createNewUser(username, password, null);
        Package toSend = new Package.PackageBuilder()
                .sender(user)
                .type(Package.NEW_USER_REGISTRATION)
                .build();
        try {
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            oos.writeObject(toSend);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            Package recieved =(Package) ois.readObject();
            if(recieved.getType() == Package.REGISTRATION_VERIFICATION) {
                OK = recieved.isOK();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return OK;
    }

    public void listProjects () {

    }

    /**
     * @param username the chosen username
     * @param password the chosen password
     * @param profilePicture
     */
    public boolean registerNewUser (String username, String password, Image profilePicture) {
        if(username != null && password != null ) {
            user = userManager.createNewUser(username, password, profilePicture);
            Package toSend = new Package.PackageBuilder()
                    .sender(user)
                    .type(Package.NEW_USER_REGISTRATION)
                    .build();

        }
        else {
            return false;
        }
        //todo felmeddelande annars? ÄNDRADE TILL BOOLEAN
        return false;
    }

    public void displayMyPages () {
    }

    public void displayCalender () {
    }

    public boolean logIn (String username, String password) {
        boolean OK = false;
        Socket socket;
        User user = user = new UserManager().createNewUser(username, password, null);
        Package toSend = new Package.PackageBuilder()
                .username(username)
                .password(password)
                .type(Package.USER_LOGGED_IN)
                .build();
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            oos.writeObject(toSend);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            Package recieved = (Package) ois.readObject();
            if(recieved.getType() == Package.LOGIN_VERIFICATION) {
                OK = recieved.isOK();
                this.user = recieved.getUserFromServer();
                client = new Client(user, socket, oos, ois);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return OK;
    }

    /**
     * @author Anna Håkansson
     *
     * Method for logging out: sending the userobject in a logoutpackage to server
     * and then disconnect client.
     */
    public void logOut () {
        Package logOutPackage = new Package.PackageBuilder()
                .sender(user)
                .type(Package.USER_LOGGED_OUT)
                .build();
        client.sendUpdate(logOutPackage);
        client.disconnect();
    }

    public void createTask(Model.Task task) {
        project.addNewTask(task);
        Package toSend = new Package.PackageBuilder()
                .task(task)
                .project(project)
                .type(Package.NEW_TASK)
                .build();
        client.sendUpdate(toSend);

    }

    public void taskEdited (Model.Task task) {
        Package toSend = new Package.PackageBuilder()
                .task(task)
                .project(project)
                .type(Package.TASK_EDITED)
                .build();
        client.sendUpdate(toSend);
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

    public void changeProject (int projectID) {

    }

    public void operation () {
    }
    public synchronized void projectUpdateRecieved(Project project) {
     HashMap <Integer, Project> tempMap = user.getProjects();
     if(tempMap.containsKey(project.getProjectID())) {
         tempMap.replace(project.getProjectID(), project);
         user.setProjects(tempMap);
     }
     if(project.getProjectID() == this.project.getProjectID()) {
         this.project = project;
     }
    }

    public void sendProjectUpdate(Project project) {
        Package toSend = new Package.PackageBuilder()
                .project(project)
                .type(Package.PROJECT_EDITED)
                .build();
    }

    public void newClient() {
        try {
            Thread.sleep(1000);
            this.client = new Client(null,null,8080);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void createNewProject(String header, String description, LocalDate deadline, String user, String creator) {
        HashMap<String, Boolean> assignees = new HashMap<>();
        assignees.put(user, false);
        assignees.put(creator, true);
        Project project = new Project.ProjectBuilder()
                    .projectName(header)
                    .description(description)
                    .deadline(deadline)
                    .assignedUser(assignees)
                    .build();
        Package toSend = new Package.PackageBuilder()
                .project(project)
                .type(Package.NEW_PROJECT)
                .build();
        client.sendUpdate(toSend);
        System.out.println(project.getProjectName());
        if(this.project == null) {
            this.project = project;
        }
    }
    public ArrayList<Model.Task> getTask(){
         return project.getTasks();
    }
    public int getTaskSize(){
        return project.getTaskSize();
    }

    public void setUpConnection() {

    }

    public void unpack(Package message) {
        switch (message.getType()) {
            case Package.PROJECT_UPDATE:
                projectUpdate(message.getProject());
                break;
            case Package.PROJECT_REMOVED:

                break;
        }
    }

    private void projectUpdate(Project project) {
        if(user.getProjects().containsKey(project.getProjectID())) {
            user.getProjects().replace(project.getProjectID(), project);
            if(project.getProjectID() == this.project.getProjectID() || this.project == null) {
                this.project = project;
            }
        } else {
            user.getProjects().put(project.getProjectID(), project);
        }
        System.out.println("Got update");
    }
}
