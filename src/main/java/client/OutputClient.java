package client;

import Model.Package;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class is the sender and sends messages to the server.
 * @Author Max Tiderman
 */
public class OutputClient {
    private ObjectOutputStream oos;

    public OutputClient(Socket socket) {
        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Här 2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Package message) {
    }
}
