package ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class is the reciever and recievs and unpackages messages from the server.
 * @Author Max Tiderman
 */
public class ClientReceiver extends Thread{
    private ObjectInputStream ois;
    private Client client;
    private volatile boolean running = true;

    public ClientReceiver(Client client, ObjectInputStream ois) {
        this.client = client;
        this.ois = ois;
        start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Package message = (Package) ois.readObject();
                Thread.sleep(1000);
            } catch (InterruptedException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
