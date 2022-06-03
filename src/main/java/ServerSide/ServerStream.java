package ServerSide;

import java.io.*;
import java.net.Socket;

/**
 * this class handles server output and server input for a client
 * @author Emma Mörk
 */
public class ServerStream {
    private Socket socket;
    private ClientHandler clientHandler;
    private ServerBuffer serverBuffer;

    public ServerStream(Socket socket, ClientHandler clientHandler, ServerBuffer serverBuffer){
        this.socket = socket;
        this.clientHandler = clientHandler;
        this.serverBuffer = serverBuffer;

        startThreads();
    }

    /**
     * creates and run two threads, one for output and one for input (from server)
     */
    private void startThreads() {
        new ServerReceiver(clientHandler, serverBuffer, socket).start();
        new ServerSender(serverBuffer, socket).start();
    }
}
