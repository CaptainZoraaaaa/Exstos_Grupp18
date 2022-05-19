package client;

import Model.Package;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class is the sender and sends messages to the server.
 * @Author Max Tiderman
 */
public class OutputClient {
    private ObjectOutputStream oos;

    public OutputClient(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public void send(Package message) {
        try {
            this.oos.writeObject(message);
            this.oos.flush();
            System.out.println("flushed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
