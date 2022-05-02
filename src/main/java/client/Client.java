package client;

import Model.Project;
import Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private int port;
    private String ip;
    private User user;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private InputClient inputClient;
    private OutputClient outputClient;

    public Client(User user, String ip, int port ) {
        this.port = port;
        this.ip = ip;
        this.user = user;
    }
    public void connect () {
        try {
            /*this.socket = new Socket(ip, port);
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());*/
            new InputClient().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void disconnect () {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendUpdate (Project project) {
        outputClient.send();
    }
}
