package client;

import java.io.ObjectInputStream;
/**
 * This class is the reciever and recievs and unpackages messages from the server.
 * @Author Max Tiderman
 */
public class InputClient extends Thread{
    private ObjectInputStream ois;
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("inputlcient");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}