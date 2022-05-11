package ServerSide;

import Model.Package;
import Model.User;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Anna Håkansson
 * Last update: 2022-05-06
 *
 * A thread class for handling connected clients. Sends and recieves
 * messages through the ServerSender och ServerReciever classes.
 */
public class ClientHandler extends Thread {
    private Socket socket; //the socket created upon connection
    private Server server; //the server
    private User user; //user associated to the client
    private ServerStream serverStream;
    private ServerBuffer<Package> serverBuffer;

    /**
     * @author Anna Håkansson
     *
     * @param socket created at connection
     * @param server ... the server
     *
     * Constructor for the ClientHandler class. Assigns the given values
     * and creates a serverSender and serverReciever to handle I/O
     */
    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        serverBuffer = new ServerBuffer<>();
        serverStream = new ServerStream(this.socket, this, serverBuffer); //TODO fixa med buffern
        System.out.println("Clienthandler created");
    }

    /**
     * @author Anna Håkansson
     * @param packageOut to be sent
     *
     * Method for sending a message from the server to the client.
     */
    public synchronized void sendMessage(Package packageOut) { //todo ändra till message sen när klassen finns
        serverBuffer.put(packageOut);
    }

    /**
     * @author Anna Håkansson
     * @param newPackage recieved
     *
     * Method that will be called upon when a message is recieved
     * in the ServerReciever.
     */
    public synchronized void packageRecieved(Package newPackage) {
        server.newPackage(this, newPackage);
        System.out.println("Package recieved in client handler");
    }

    /**
     * @author Anna Håkansson
     *
     * @param user associeted to the client is needed to be able to
     *             add a client to the clientList.
     *
     * Method for adding the client to the clientList.
     */
    public synchronized void addToServerClientList(User user) {
        if (user != null) { //if user isnt null
            this.user = user; //assign the user to instance variable
            server.addClient(user.getUsername(), this); //add the username (key) and this client (value) to the servers clientMap
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
