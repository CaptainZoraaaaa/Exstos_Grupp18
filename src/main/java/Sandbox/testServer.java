package Sandbox;

import Model.Package;

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
                    try {
                        Package p = (Package) ois.readObject();
                        switch(p.getType()){
                            case Package.NEW_USER_REGISTRATION -> {
                                System.out.println(p.getSender().getUsername());
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Person connectade");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
