package ServerSide;

import Model.Project;
import Model.Task;
import Model.User;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author Anna Håkansson
 * Last update: 2022-04-28
 *
 * This is the server-class for storing data and managing requests and connections.
 */
public class Server {
    private Connection connection;
 //   private ServerController serverController;
    private HashMap<String, ClientHandler> clientMap;
    private HashMap<String, User> userMap;
    private HashMap<Integer, Project> projectMap;
    private final int port = 8080;

    /**
     * @author Anna Håkansson
     *
     * Constructor for server. The maps gets assigned saved values
     * from .dat-files and then the server connects (e.g. starting the serversocket)
     */
    public Server() {
        //clientMap = readMapFromFile("client"); //todo vi behöver väl inte läsa in klienter?
        //userMap = readMapFromFile("user");
        //projectMap = readMapFromFile("project");
        connect();
    }

    /**
     * @author Anna Håkansson
     *
     * @param object the one to get an ID
     * @return the new ID (or -1 if input was invalid)
     *
     * This methods assign the right type based on what instance
     * the object is for. It then sends the type into a method
     * where an ID from the right .dat-file will be returned. It
     * then calls on the writeNewID-method to make sure it will be
     * a new ID the next time someone want's an ID.
     */
    public synchronized int createNewID(Object object) {
       int id = -1; //
       String type; //type of object
        if(object instanceof Project) { //if its a project object
            type = "projectID"; //assign type
        }
        else if (object instanceof User) { //if its a user object
            type = "userID"; //assign type
        }
        else if (object instanceof Task) { //if its a task object
            type = "taskID"; //assign type
        }
        else { //else type is null
            type = null;
        }

        if(type != null) { //if type not null
            id = getIDFromFile(type); //get ID from .dat-file and assign to ID variable
            writeNewID(id, type); //write a new ID for the next time someone wants an ID
        }
        return id;
    }

    /**
     * @author Anna Håkansson
     *
     * @param type the class that needs an ID
     * @return the ID fetched from file and -1 if the reading failed
     *
     * This method uses the type-string to get access
     * to the right dat-file and returns the ID written
     * there.
     */
    public synchronized int getIDFromFile(String type) {
        int ID = -1; //if it doesnt work it will return the "fail"-value
        String filename = String.format("files/%s.dat", type); //format the string to get the right filename

        try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))) { //create stream
            ID = dis.readInt(); //read ID
        } catch (IOException e) {
            System.err.println("Failure in Server.readIDFile due to" + e);
        }

        return ID;
    }

    /**
     * @author Anna Håkansson
     *
     * @param currentID the ID that was used last
     * @param type that needs it's ID-value incremented
     *
     * This method takes the previously used ID, increments
     * it and writes the new file to the right dat file according
     * to the "type"-string.
     */
    public synchronized void writeNewID(int currentID, String type) {
        String filename = String.format("files/%s.dat", type); //format string to get right file
        int newID = currentID + 1; //increment ID
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))){ //create stream
            dos.write(newID); //write the new ID
            dos.flush();
        } catch (Exception e) {
            System.err.println("Error in Server.writeNewID due to" + e);
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param username of the user connected to the client
     * @param client the connected client
     *
     * Method for adding a clienthandler-object to the client hashmap with
     * its related users name.
     */
    public synchronized void addClient(String username, ClientHandler client) { //todo lagt till string username samt ändrat client till clienthandler
        if (!clientMap.containsKey(username)) { //if the client map does not already contain the username
            clientMap.put(username, client); //put the username (key) and client (value) in the clientMap
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param project to be added
     * Method for adding a project into the project hashmap if
     * it doesn't already contain a project with same ID.
     */
    public synchronized void addProject(Project project) {
        if (!projectMap.containsKey(project.getProjectID())) { //if the projectmap does not already contain the projectID
            projectMap.put(project.getProjectID(), project); //put the projectID (key) and the project (value) in the projectMap
        }
    }

    /**
     * @author Anna Håkansson
     * @param user to be added
     * Method for adding a user into the user hashmap
     * if it doesn't already contain a user with same username.
     */
    public synchronized void addUser(User user) {
        if (!userMap.containsKey(user.getUsername())) { //if the userMap does not already contain the username
            userMap.put(user.getUsername(), user); //put the username (key) and the user (object) in the userMap
        }
    }

    /**
     * @param logText to be added to the log
     * Saves the LocalDateTime at the executing moment and appends
     * it together with the logtext to the logtext file.
     */
    public synchronized void writeLog(String logText) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/log.txt")); //create writer
            bw.append(String.format("%s: %s", LocalDateTime.now(), logText)); //append the time and the logtext (e.g. add an extra line instead of overwriting)
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.err.println("Failure in Server.writeLog due to" + e);
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param username used to sign in with
     * @param password used to sign in with
     * @return if the username and password is a match or not
     *
     * Method for checking if the log in credentials are valid.
     */
    public synchronized boolean verifyCredentials(String username, String password) {
        if(userMap.containsKey(username) && userMap.get(username).getPassword().equals(password)) { //if the usermap contains this username and the password related to that username is the same as the parameter
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @author Anna Håkansson
     * @param user to be verified
     * @return if the username is unique in the map or not
     *
     * Method for checking if a new user used a unique
     * username or not.
     */
    public synchronized boolean verifyRegistration(User user) { //todo denna metoden bara verifierar, addUser behöver anvcändas också
        if(!userMap.containsKey(user.getUsername())) { //if the usermap does not contain the username
            return true;
        }
        return false;
    }

    /**
     * @author Anna Håkansson
     * @param user to be deleted
     *
     * Method for removing a user from the userMap (e.g. the system)
     */
    public synchronized void deleteUser(User user) {
        if (user != null) { //if the user isnt null
            userMap.remove(user.getUsername());  //remove the user from the usermap
        }

    }

    /**
     * @author Anna Håkansson
     *
     * Method for initialize the connection with the server socket.
     */
    public void connect() {
        connection = new Connection(port, this);

    }

    /**
     * @author Anna Håkansson
     *
     * Method for disconnecting the serversocket by setting
     * the boolean flag in the connections run-method to false.
     */
    public void disconnect() {
        connection.setAlive(false);
    }

    /**
     * @author Anna Håkansson
     *
     * @param map to be written to file
     * @param type the type of map (user/client/project)
     *
     * Method for writing a hashmap to a .dat-file with an
     * object output stream.
     */
    public synchronized void writeMapToFile(HashMap map, String type) {
        String filename = String.format("files/%s.dat", type); //format string to get right filename
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) { //create stream
            oos.writeObject(map); //write
            oos.flush();
        } catch (FileNotFoundException e) {
            System.err.println("Failure in Server.writeMapToFile due to" + e);
        } catch (IOException e) {
            System.err.println("Failure in Server.writeMapToFile due to" + e);
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param type of map to be read
     * @return the map read from the .dat-file
     *
     * Method for reading a hashmap from a .dat-file by
     * giving the wanted type of map.
     */
    public synchronized HashMap readMapFromFile(String type) {
        String filename = String.format("files/%s.dat", type); //format the string to get the right filename
        HashMap map = null; //initialize map
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))){ //create stream
            map = (HashMap) ois.readObject(); //read map
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failure in Server.readMapFromFile due to" + e);
        }
        return map;
    }

    public synchronized void setClientMap(HashMap<String, ClientHandler> clientMap) {
        this.clientMap = clientMap;
    }

    public synchronized void setProjectMap(HashMap<Integer, Project> projectMap) {
        this.projectMap = projectMap;
    }

    public synchronized void setUserMap(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }

    public synchronized Connection getConnection() {
        return connection;
    }

    public synchronized HashMap<Integer, Project> getProjectMap() {
        return projectMap;
    }

    public synchronized HashMap<String, ClientHandler> getClientMap() {
        return clientMap;
    }

    public synchronized HashMap<String, User> getUserMap() {
        return userMap;
    }

    public int getPort() {
        return port;
    }
}
