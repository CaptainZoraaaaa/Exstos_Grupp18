package ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Anna Håkansson
 * Last update: 2022-04-28
 *
 * Thread class for keeping a serversocket
 * running and accepting connections from clients
 */
public class Connection extends Thread {
    private int port; //port to be used
    private Server server; //server the connection serves
    private volatile boolean alive = true; //flag for stopping thread and closing connection
    private ServerSocket serverSocket;

    /**
     * @author Anna Håkansson
     *
     * @param port to open for connections
     * @param server the connection serves
     *
     * Constructor for the Connection class.
     * Assigns values for port and server
     * variables and starting it's run method.
     */
    public Connection(int port, Server server) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.port = port;
        this.server = server;
        start();
    }

    /**
     * @author Anna Håkansson
     *
     * Run-method for the Connection thread.
     * Creates a serversocket and listens for connections.
     * If a client connects it will create a new clienthandler object.
     */
    @Override
    public void run() {
         { //create new serversocket
            while (alive) { //while the flag is true
            try {
                    Socket socket = serverSocket.accept(); //assing the client to a socket
                    System.out.println("A client just connected.");
                    new ClientHandler(socket, server); //create a new ClientHandler
                } catch (IOException e) {
                    System.err.println("Failure in Connection.run when accepting client due to" + e);
                }
            }
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
