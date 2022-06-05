package ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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
        String logtext;
        try(ServerSocket serverSocket = new ServerSocket(port)) { //create new serversocket'
            serverSocket.setReuseAddress(true);
            logtext = "Server is running";
            ClientHandler clientHandler;
            System.out.println(logtext);
            ServerFileManager.writeLog(logtext);
            while (alive) { //while the flag is true
            try {
                    Socket socket = serverSocket.accept(); //assing the client to a socket
                    socket.setReuseAddress(true);
                    clientHandler = new ClientHandler(socket, server);
                    logtext = "A client just connected.";
                    ServerFileManager.writeLog(logtext); //create a new ClientHandler
                } catch (IOException e) {
                    logtext = String.format("Failure in Connection.run when accepting client due to %s", e);
                    System.err.println(logtext);
                    ServerFileManager.writeLog(logtext);
                }
            }
        } catch (IOException e) {
            logtext = String.format("Failure in Connection.run when creating Serversocket due to %s", e);
            System.err.println(logtext);
            ServerFileManager.writeLog(logtext);
        }
        ServerFileManager.writeLog("Server is closing down. Bye bye!");
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
