package client;

import java.io.BufferedInputStream;
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

    public InputClient(Client client, Socket socket) {
        try {
            this.client = client;
            //start();
            this.ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (running) {
            try {
                client.testning();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
