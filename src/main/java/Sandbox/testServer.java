package Sandbox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class testServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public testServer(int port) {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Connections().start();
    }

    private class Connections extends Thread{
        public void run() {
            Socket socket;
            System.out.println("Loading...");
            System.out.println("Server operational.");
            while (true) {
                try {
                    socket = serverSocket.accept();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    System.out.println("Person connectade");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
