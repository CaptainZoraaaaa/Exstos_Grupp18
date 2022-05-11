package ServerSide;

import Model.Package;
import Model.Project;
import Model.User;

import java.util.HashMap;
import java.util.Map;

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
        return server.getIDFromFile(type);
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
        server.writeNewID(currentID, type);
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
     * @author Linnéa Flystam
     *
     * @param project a project that a user wants to add
     *
     * Method that calls to method with the same name in class Server with incoming parameter.
     */
    public void addProject(Project project) {
        server.addProject(project);
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
     * @author Linnéa Flystam
     *
     * @param logText text that adds to log
     *
     * Method that calls to method with the same name in class Server with incoming parameter.
     */
    public void writeLog(String logText) {
        server.writeLog(logText);
    }

    /**
     * @author Linnéa Flystam & Anna Håkansson
     *
     * @param username the users username
     * @param password the users password
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
     */
    public void verifyCredentials(ClientHandler clientHandler, String username, String password) {
        User user;
        boolean loginOK = server.verifyCredentials(username, password);
        if (loginOK) {
            user = server.getUserMap().get(username);
        }
        else {
            user = null;
        }
        Package loginReply = new Package.PackageBuilder()
                .ok(loginOK)
                .userFromServer(user).
                type(Package.LOGIN_VERIFICATION).
                build();
        clientHandler.sendMessage(loginReply);
    }

    /**
     * @author Linnéa Flystam & Anna Håkansson
     *
     * @param user user to be verified
     * @return call to the method in class Server
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
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
        Package reply = new Package.PackageBuilder()
                .ok(registrationOK)
                .userFromServer(newUser)
                .type(Package.REGISTRATION_VERIFICATION)
                .build();
        clientHandler.sendMessage(reply);
    }

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
            }
        }
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
        server.writeMapToFile(map, type);
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
        return server.readMapFromFile(type);
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
     *
     * @param clientHandler .
     * @param user
     */
    public void userLoggedIn(ClientHandler clientHandler, User user) {
        server.addOnlineUser(user);
        server.addClient(user.getUsername(), clientHandler);
        //TODO skicka user tillbaka
    }

    /**
     * @author Anna Håkansson
     *@param username
     * @param project
     */
    public void userAssignedToProject(String username, Project project) {
        server.addUserToProject(username, project);
        sendOutProjectUpdate(project);

    }

    /**
     *
     */
    public void sendOutProjectUpdate(Project project) {
        Package toSend = new Package.PackageBuilder()
                .project(project)
                .type(Package.PROJECT_UPDATE)
                .build();
        server.sendProjectUpdateToUsers(toSend);
    }

    public void userRemovedFromProject(User sender, Project project) {
        server.removeUserFromProject(sender, project);
        sendOutProjectUpdate(project);
    }
}
