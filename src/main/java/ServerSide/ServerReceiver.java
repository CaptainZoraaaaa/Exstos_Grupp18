package ServerSide;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ServerReceiver holds a inputstream receiving information from server
 */
public class ServerReceiver extends Thread{
    private Package packageObject;
    private ClientHandler clientHandler;
    private ServerBuffer serverBuffer;
    private Socket socket;

    public ServerReceiver(ClientHandler clientHandler, ServerBuffer serverBuffer, Socket socket) {
        this.clientHandler = clientHandler;
        this.serverBuffer = serverBuffer;
    }

    /**
     * thread for receiving data server -> client.
     * reads object with readObject() and save the data to a variable which then is
     * passed along to a clientHandlet-method
     */
    @Override
    public void run() {
        while (!Thread.interrupted()){
            try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {
                packageObject = (Package) ois.readObject();
                clientHandler.packageRecieved(packageObject);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
