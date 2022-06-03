package client;

import Model.DataPackage;
import Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class makes sure that every person that logges in can communicate with the server and that guis are shown the right way.
 * @Author Max Tiderman
 */
public class Client {

    private int port;
    private String ip;
    private Socket socket;
    private User user;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private InputClient inputClient;
    private OutputClient outputClient;

    /**
     * This is the constructor and prepares the connecion to the server
     * @param user - What user just logged in
     * @param ip - ip for the server
     * @param port - port to communicate through
     * @Author Max Tiderman
     */
    public Client(User user, String ip, int port ) {
        this.ip = ip;
        this.port = port;
        connect();
    }


    public Client(User user, Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
        this.user = user;
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
        connect();
    }

    /**
     * This method is going to connect the client to the server aswell as macking the streams to communicate with the server
     * @Author Max Tiderman
     */
    public void connect () {
        try {
            new ThreadHandler(this).start();
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
            inputClient.setRunning(false);
            oos.close();
            ois.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is going send different messages to the server
     * @Author Max Tiderman
     */
    public void sendUpdate (DataPackage message) {
        outputClient.send(message);
        System.out.println("sending update");
    }
    //// TODO: 2022-06-03 Javadoc. 
    private class ThreadHandler extends Thread{
        private Client client;
        public ThreadHandler(Client client){
            this.client = client;
        }
        //// TODO: 2022-06-03 Javadoc.
        @Override
        public void run() {
            outputClient = new OutputClient(oos);
            inputClient = new InputClient(client,ois);
        }
    }
}
