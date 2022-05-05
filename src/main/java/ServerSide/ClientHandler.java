package ServerSide;

import Model.User;

import java.net.Socket;

/**
 * @author Anna Håkansson
 * Last update: 2022-05-05
 *
 * A thread class for handling connected clients. Sends and recieves
 * messages through the ServerSender och ServerReciever classes.
 */
public class ClientHandler extends Thread {
    private Socket socket; //the socket created upon connection
    private Server server; //the server
    private User user; //user associated to the client
    private ServerBuffer serverBuffer = new ServerBuffer();
  //  private ServerStream serverStream;

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
        //serverStream = new ServerStream(socket, this)

        createServerStream();

    }

    /**
     * @author Emma Mörk
     *               creates new ServerStream-object
     */
    private void createServerStream() {
        new ServerStream(socket, this, serverBuffer);
    }


    /**
     * @author Anna Håkansson
     * @param packgeItem to be sent
     *
     * Method for sending a message from the server to the client.
     */
    public synchronized void sendMessage(Package packgeItem) { //todo ändra till message sen när klassen finns
        serverBuffer.put(packgeItem);
    }

    /**
     * @author Anna Håkansson
     * @param newPackage package received
     */
    public synchronized void packageRecieved(Package newPackage) { //todo ändra till message när klassen finns
        //TODO när upppackningsklassen är implementerad
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
}
