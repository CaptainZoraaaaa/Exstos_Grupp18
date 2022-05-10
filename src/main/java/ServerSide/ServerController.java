package ServerSide;

import Model.Project;
import Model.User;

import java.util.HashMap;

public class ServerController {

    private Server server;

    /**
     * @author Linnéa Flystam
     *
     * Constructor for ServerController that creates object of class Server
     */
    public ServerController() {
        server = new Server();
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
     * @author Linnéa Flystam
     *
     * @param username the users username
     * @param password the users password
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
     */
    public boolean verifyCredentials(String username, String password) {
        return server.verifyCredentials(username, password);
    }

    /**
     * @author Linnéa Flystam
     *
     * @param user user to be verified
     * @return call to the method in class Server
     *
     * Method that calls to method with the same name in class Server with incoming parameters.
     */
    public boolean verifyRegistration(User user) {
        return server.verifyRegistration(user);
    }

    /**
     * @author Linnéa Flystam
     *
     * @param user user to be deleted
     *
     * Method that calls to method with the same name in class Server with incoming parameter.
     */
    public void deleteUser(User user) {
        server.deleteUser(user);
    }

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

}
