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

public class Controller {
    private Task task;
    private User user;
    private Client client;
    private ArrayList<Project> projects = new ArrayList<>();
    private Project activeProject = new Project();
    private TaskManager taskManager;
    private UserManager userManager = new UserManager();
    private ProjectManager projectManager;
    private ServerStub serverStub = new ServerStub();
    private static Controller controller = new Controller();
    private static ClientBuffer clientBuffer = new ClientBuffer();

    private String loggedInUser;

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

    public boolean checkUsername (String username) { //todo ändrat parametrar

       // clientBuffer.put();
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

    public void logOut () {
    }

    public void createTask(Model.Task task) {
        activeProject.addNewTask(task);
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

    public void newClient() {
        try {
            Thread.sleep(1000);
            this.client = new Client(null,null,8080);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void createNewProject(String header, String description, LocalDate deadline, String user, String creator) {
        this.activeProject = new Project.ProjectBuilder().projectName(header).description(description).deadline(deadline).build();
        projects.add(activeProject);
        /*Package toSend = new Package.PackageBuilder()
                .project(activeProject)
                .type(Package.NEW_PROJECT)
                .build();
        client.sendUpdate(toSend);*/
    }
    public void setCurrentTask(String projectName){
        for (Project customer : projects) {
            if (customer.getProjectName().equals(projectName)) {
                this.activeProject = customer;
            }
        }
    }

    public ArrayList<Model.Task> getTask(){
         return activeProject.getTasks();
    }
    public int getTaskSize(){
        return activeProject.getTaskSize();
    }
    public void setUpConnection() {

    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public ArrayList<Project> getAllProject() {
        return projects;
    }

}
