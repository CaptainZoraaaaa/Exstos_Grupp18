package ClientSide;

import Model.Package;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is the sender and sends messages to the server.
 * @Author Max Tiderman
 */
public class ClientSender {
    private ObjectOutputStream oos;
    private ClientBuffer clientBuffer;

    public ClientSender(ObjectOutputStream ois, ClientBuffer clientBuffer) {
        this.clientBuffer = clientBuffer;
        this.oos = oos;
    }

    /**
     * @param message
     * @author Emma Mörk
     * send latest message put in ClientBuffer using get()-method
     */
    public void send(Package message) {
        try {
            this.oos.writeObject(clientBuffer.get());
            this.oos.flush();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
