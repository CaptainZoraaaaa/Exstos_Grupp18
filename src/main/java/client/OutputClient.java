package client;

import Model.DataPackage;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is the sender and sends messages to the server.
 * @Author Max Tiderman
 */
public class OutputClient {
    private ObjectOutputStream oos;

    public OutputClient(ObjectOutputStream oos) {
        this.oos = oos;
    }
    //// TODO: 2022-06-03 javadoc.
    public void send(DataPackage message) {
        try {
            this.oos.writeObject(message);
            this.oos.flush();
            System.out.println("flushed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
