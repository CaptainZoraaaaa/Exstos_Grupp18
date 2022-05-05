package ServerSide;

import Model.Project;
import Model.Task;
import Model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author Anna Håkansson
 * Last update: 2022-04-28
 *
 * This is the server-class for storing data and managing requests and connections.
 */
public class Server {
    private int port = 8080;
    private ServerSocket serverSocket;
    public Server( ) {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Connection().start();
    }

    public void addClient(String username, ClientHandler clientHandler) {
    }

    private class Connection extends Thread {

        public void run() {
            Socket socket;
            System.out.println("Loading...");
            System.out.println("Server operational.");
            while (true) {
                try {
                    socket = serverSocket.accept();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                    System.out.println("new connection");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
