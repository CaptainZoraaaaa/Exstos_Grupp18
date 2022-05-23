package client;

import Model.DataPackage;
import controller.Controller;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * This class is the reciever and recievs and unpackages messages from the server.
 * @Author Max Tiderman
 */
public class InputClient extends Thread{
    private ObjectInputStream ois;
    private Client client;
    private volatile boolean running = true;
    private Controller controller = Controller.getInstance();

    public InputClient(Client client, Socket socket) {
        this.client = client;
        try {
            this.ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public InputClient(Client client, ObjectInputStream ois) {
        this.client = client;
        this.ois = ois;
        start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                DataPackage message = (DataPackage) ois.readObject();
                controller.unpack(message);
                Thread.sleep(1000);
            } catch (EOFException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
