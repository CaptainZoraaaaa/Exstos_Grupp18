package ServerSide;

import Model.DataPackage;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ServerReceiver holds a inputstream receiving information from server
 */
public class ServerReceiver extends Thread{
    private DataPackage dataPackageObject;
    private ClientHandler clientHandler;
    private ServerBuffer serverBuffer;
    private Socket socket;
    private ObjectInputStream ois;

    public ServerReceiver(ClientHandler clientHandler, ServerBuffer serverBuffer, Socket socket) {
        this.clientHandler = clientHandler;
        this.serverBuffer = serverBuffer;
        this.socket = socket;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * thread for receiving data server -> client.
     * reads object with readObject() and save the data to a variable which then is
     * passed along to a clientHandlet-method
     */
    @Override
    public void run() {

        while (!socket.isClosed()) {
            try {
                dataPackageObject = (DataPackage) ois.readObject();
                clientHandler.packageRecieved(dataPackageObject);
            } catch (EOFException e) {
                //bara chilla
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
