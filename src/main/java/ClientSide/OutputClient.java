package ClientSide;

import Model.Package;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is the sender and sends messages to the server.
 * @Author Max Tiderman
 */
public class OutputClient {
    private ObjectOutputStream oos;

    public OutputClient(ObjectOutputStream ois) {
        this.oos = oos;
    }

    public void send(Package message) {
        try {
            this.oos.writeObject(message);
            this.oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
