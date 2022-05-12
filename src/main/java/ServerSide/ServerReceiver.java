package ServerSide;

import Model.Package;

import java.io.BufferedInputStream;
import java.io.EOFException;
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
        this.socket = socket;
    }

    /**
     * thread for receiving data server -> client.
     * reads object with readObject() and save the data to a variable which then is
     * passed along to a clientHandlet-method
     */
    @Override
    public void run() {

            try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {
                while (!socket.isClosed()) {
                    packageObject = (Package) ois.readObject();
                    clientHandler.packageRecieved(packageObject);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("end of ");
            }
    }
}
