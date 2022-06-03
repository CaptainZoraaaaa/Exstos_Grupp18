package ServerSide;

import Model.DataPackage;
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
    private ServerBuffer<DataPackage> serverBuffer;

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
     * @param dataPackageOut to be sent
     *
     * Method for sending a message from the server to the client.
     */
    public synchronized void sendMessage(DataPackage dataPackageOut) { //todo ändra till message sen när klassen finns
        serverBuffer.put(dataPackageOut);
        notifyAll();
    }

    /**
     * @author Anna Håkansson
     * @param newDataPackage recieved
     *
     * Method that will be called upon when a message is recieved
     * in the ServerReciever.
     */
    public synchronized void packageReceived(DataPackage newDataPackage) {
        server.newPackage(this, newDataPackage);
        System.out.println("DataPackage recieved in client handler");
    }

    /**
     * Closes the socket
     * @author Anna Håkansson.
     */
    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
