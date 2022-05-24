package ServerSide;

import Model.DataPackage;
import Model.Project;
import Model.Task;
import Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Linnéa Flystam & Anna Håkansson
 */
public class ServerController {

    private final static String TYPE_TASK = "task";
    private final static String TYPE_PROJECT = "project";
    private final static String TYPE_USER = "user";

    private Server server;

    /**
     * @author Linnéa Flystam
     *
     * Constructor for ServerController that creates object of class Server
     */
    public ServerController() {
        server = new Server(this);
    }

    /**
     * @author Linnéa Flystam
     *
     * @param object to get an ID
     * @return call to the method in class Server
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
     */
    public int createNewID(Object object) {
        return server.createNewID(object);
    }

    /**
     * @author Linnéa Flystam
     *
     * @param type the class that asks for an ID
     * @return call to the method in class Server
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
     */
    public int getIDFromFile(String type) {
        return ServerFileManager.getIDFromFile(type);
    }

    /**
     * @author Linnéa Flystam
     *
     * @param currentID the ID that was used last
     * @param type the type that needs it's ID-value incremented
     * @return call to the method in class Server
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
     */
    public void writeNewID(int currentID, String type) {
        ServerFileManager.writeNewID(currentID, type);
    }

    /**
     * @author Linnéa Flystam
     *
     * @param username of user that is connected to the client
     * @param client a connected client
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
     */
    public void addClient(String username, ClientHandler client) {
        server.addClient(username, client);
    }

    /**
     * @author Linnéa Flystam & Anna håkansson
     *
     * @param project a project that a user wants to add
     *
     * Method that calls to method with the same name in class Server with incoming parameter.
     */
    public void newProject(Project project) {
        int projectID = ServerFileManager.getIDFromFile(TYPE_PROJECT);
        writeNewID(projectID, TYPE_PROJECT);
        project.setProjectID(projectID);
        server.addProject(project);
        Project toSend = getProjectMap().get(project.getProjectID());
        sendOutProjectUpdate(toSend);
        ServerFileManager.writeMapToFile(server.getProjectMap(), TYPE_PROJECT);
    }

    /**
     * @author Linnéa Flystam
     *
     * @param user that is to be added
     *
     * Method that calls to method with the same name in class Server with incoming parameter.
     */
    public void addUser(User user) {
        server.addUser(user);
    }


    /**
     * @author Linnéa Flystam & Anna Håkansson
     *
     * @param clientHandler the client that tried to log in
     * @param username the users username
     * @param password the users password
     *
     * Method that checks if the credentials are valid, and if they are
     * it fetches the user object from userMap, adds onlineUser, adds the client
     * to clientMap and sends the user object back to the client together with boolean
     * loginOK.
     *
     * If credentials aren't valid, it sends back a user with null value and loginOK with
     * value false.
     */
    public void verifyCredentials(ClientHandler clientHandler, String username, String password) {
        User user;
        ArrayList<Project> projectList = new ArrayList<>();
        boolean loginOK = server.verifyCredentials(username, password); //check if credentials are right
        if (loginOK) { //if they were
            user = server.getUserMap().get(username); //get user from userMap
            server.addOnlineUser(user); //add it to online users
            server.addClient(user.getUsername(), clientHandler); //add the client to clientMap
            projectList = getUsersProjects(username);
        }
        else {
            user = null; //else user is null
        }
        DataPackage loginReply = new DataPackage.PackageBuilder()
                .verificationSuccess(loginOK)
                .userFromServer(user)
                .projectList(projectList)
                .packageType(DataPackage.LOGIN_VERIFICATION).
                build();
        clientHandler.sendMessage(loginReply); //send reply with if its ok and correct user if it was ok
    }

    /**
     * @author Linnéa Flystam & Anna Håkansson
     *
     * @param clientHandler client that is registering
     * @param user user to be verified
     *
     * Method that checks if the username is unique and builds a new user
     * object, adds it to usermap and sends it back to the client.
     */
    public void newRegistration(ClientHandler clientHandler, User user) {
        boolean registrationOK = server.verifyRegistration(user);
        User newUser;
        if (registrationOK) {
            newUser = buildNewUser(user);
        }
        else {
            newUser = null;
        }
        addUser(user);
        DataPackage reply = new DataPackage.PackageBuilder()
                .verificationSuccess(registrationOK)
                .userFromServer(newUser)
                .packageType(DataPackage.REGISTRATION_VERIFICATION)
                .build();
        clientHandler.sendMessage(reply);
        ServerFileManager.writeMapToFile(server.getUserMap(), TYPE_USER);
    }

    /**
     * @param user
     * @return
     */
    public User buildNewUser(User user) {
        return new User.UserBuilder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

    }

    /**
     * @author Linnéa Flystam
     *
     * @param user user to be deleted
     *
     * Method that calls to method with the same name in class Server with incoming parameter.
     */
    public void deleteUser(User user) { //todo kanske kommer vi behöva hämta nycklar för att skicka objekt

        for(Map.Entry<Integer, Project> entry : server.getProjectMap().entrySet()) {
            if(entry.getValue().getAssignedUsers().containsKey(user.getUsername())) {
                server.removeUserFromProject(user, entry.getValue());
                Project toSend = getProjectMap().get(entry.getValue().getProjectID());
                sendOutProjectUpdate(toSend);
            }
        }
        ServerFileManager.writeMapToFile(server.getUserMap(), TYPE_USER);
    } //lägg till att ta bort från projekt

    /**
     * @author Linnéa Flystam
     *
     * Method that calls to method with the same name in class Server.
     */
    public void connect() {
        server.connect();
    }

    /**
     * @author Linnéa Flystam
     *
     * Method that calls to method with the same name in class Server.
     */
    public void disconnect() {
        server.disconnect();
    }

    /**
     * @author Linnéa Flystam
     *
     * @param map hashmap that will be overwritten to file
     * @param type type of map
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
     */
    public void writeMapToFile(HashMap map, String type) {
        ServerFileManager.writeMapToFile(map, type);
    }

    /**
     * @author Linnéa Flystam
     *
     * @param type type of map
     * @return call to the method in class Server
     *
     * Method that calls to method with the same name in class Server with incoming parameter.
     */
    public HashMap readMapFromFile(String type) {
        return ServerFileManager.readMapFromFile(type);
    }

    public void setClientMap(HashMap<String, ClientHandler> clientMap) {
        server.setClientMap(clientMap);
    }

    public void setUserMap(HashMap<String, User> userMap) {
        server.setUserMap(userMap);
    }

    public Connection getConnection() {
       return server.getConnection();
    }

    public HashMap<Integer, Project> getProjectMap() {
        return server.getProjectMap();
    }

    public HashMap<String, ClientHandler> getClientMap() {
        return server.getClientMap();
    }

    public HashMap<String, User> getUserMap() {
        return server.getUserMap();
    }

    public int getPort() {
        return server.getPort();
    }



    /**
     * @author Anna Håkansson
     *@param username
     * @param project
     */
    public void userAssignedToProject(String username, Project project) {
        server.addUserToProject(username, project);
        Project toSend = getProjectMap().get(project.getProjectID());
        sendOutProjectUpdate(toSend);

    }

    /**
     *@author Anna Håkansson
     */
    public void sendOutProjectUpdate(Project project) {
        Task[] tasks = project.getTasks().toArray(new Task[0]);
        DataPackage toSend = new DataPackage.PackageBuilder()
                .project(project)
                .packageType(DataPackage.PROJECT_UPDATE)
                .taskList(tasks)
                .build();
        server.sendProjectUpdateToUsers(toSend);
    }
    /**
     *@author Anna Håkansson
     */
    public void userRemovedFromProject(User user, Project project) {
        server.removeUserFromProject(user, project);
        sendOutProjectUpdate(project);
    }
    /**
     *@author Anna Håkansson
     */
    public void userLoggedOut(User user) {
        server.removeOnlineUser(user);
        server.getClientMap().get(user.getUsername()).disconnect();
        server.getClientMap().remove(user.getUsername());
    }
    /**
     *@author Anna Håkansson
     */
    public synchronized void newTask(Task task, Project project) {
        int taskID = getIDFromFile(TYPE_TASK);
        writeNewID(taskID, TYPE_TASK);
        task.setTask_id(taskID);
        Project toSend = server.addTaskToProject(task, project);
       // getProjectMap().get(project.getProjectID());
        sendOutProjectUpdate(toSend);
        ServerFileManager.writeMapToFile(server.getProjectMap(), TYPE_PROJECT);
    }
    /**
     *@author Anna Håkansson
     */
    public void taskEdited(Task task, Project project) {
        server.updateTask(task, project);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Project toSend = getProjectMap().get(project.getProjectID());
        sendOutProjectUpdate(toSend);
    }
    /**
     *@author Anna Håkansson
     */
    public void taskRemoved(Task task, Project project) {
        server.removeTask(task, project);
        Project toSend = getProjectMap().get(project.getProjectID());
        sendOutProjectUpdate(toSend);
    }
    /**
     *@author Anna Håkansson
     */
    public void projectEdited(Project project) {
        server.updateProject(project);
        Project toSend = getProjectMap().get(project.getProjectID());
        sendOutProjectUpdate(toSend);

        ServerFileManager.writeMapToFile(server.getProjectMap(), TYPE_PROJECT);
    }
    /**
     *@author Anna Håkansson
     */
    public void projectRemoved(Project project) {
        DataPackage toSend = new DataPackage.PackageBuilder()
                .packageType(DataPackage.PROJECT_REMOVED)
                .project(project)
                .build();
        for(Map.Entry<String, User> user : getUserMap().entrySet()) {
            if(server.getOnlineUsers().contains(user.getValue())) {
                getClientMap().get(user.getKey()).sendMessage(toSend);
            }
            else {
                user.getValue().getProjects().remove(project.getProjectID());
            }
        }
        server.deleteProject(project);
    }

    public ArrayList<Project> getUsersProjects(String username) {
        ArrayList<Project> usersProjects = new ArrayList<>();
        for(Map.Entry<Integer, Project> projectEntry : server.getProjectMap().entrySet()) {
            Project project = projectEntry.getValue();
            HashMap<String, Boolean> assignees = project.getAssignedUser();
            if(assignees.containsKey(username)) {
                usersProjects.add(project);
            }
        }
        return usersProjects;
    }
}
