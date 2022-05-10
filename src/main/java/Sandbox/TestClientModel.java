package Sandbox;

import Model.Package;
import Model.User;
import client.Client;
import client.InputClient;
import client.OutputClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 *
 *
 *
 *
 * DETTA ÄR EN LEKKLASS FÖR ATT TESTA ANNAT IP
 */


/**
 * This class makes sure that every person that logges in can communicate with the server and that guis are shown the right way.
 * @Author Max Tiderman
 */
public class TestClientModel extends Client {

    private int port;
    private String ip;
    private User user;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private InputClient inputClient;
    private OutputClient outputClient;

    /**
     * This is the construktor and prepares the connecion to the server
     * @param user - What user just logged in
     * @param ip - ip for the server
     * @param port - port to communicate through
     * @Author Max Tiderman
     */
    public TestClientModel(User user, String ip, int port ) {
        super(user, ip, port);

    }

    /**
     * This method is going to connect the client to the server aswell as macking the streams to communicate with the server
     * @Author Max Tiderman
     */
    public void connect () {
        try {
            this.socket = new Socket("localhost", 8080);
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("io created");
            //new InputClient().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is going to close the connection to the server
     * @Author Max Tiderman
     */
    public void disconnect () {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is going send different messages to the server
     * @Author Max Tiderman
     */
    public void sendUpdate (Package message) {
        outputClient.send(message);
    }

    public void createIO(ObjectOutputStream oos, ObjectInputStream ois) {
        outputClient = new OutputClient(oos);
        inputClient = new InputClient(this, ois);
    }

    private class ThreadHandler extends Thread{
        private TestClientModel client;
        public ThreadHandler(TestClientModel client){
            this.client = client;
        }

        @Override
        public void run() {
            outputClient = new OutputClient(oos);
            inputClient = new InputClient(client,ois);
            System.out.println("I/O running");
        }
    }
    public void testning(){
        System.out.println("körs");
    }
}
