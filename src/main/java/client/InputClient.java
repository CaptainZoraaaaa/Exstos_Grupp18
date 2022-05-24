package client;

import Model.DataPackage;
import controller.Controller;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

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

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            try {
                DataPackage message = (DataPackage) ois.readObject();
                controller.unpack(message);
                if(message.getPackageType() == DataPackage.PROJECT_UPDATE) {
                    System.out.println(message.getProject().getTasks().size());
                    System.out.println(message.getTestString());
                }
                Thread.sleep(1000);
            } catch (EOFException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                System.out.println("Socket is closed. Bye bye!");
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
