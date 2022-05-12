package Sandbox;

import Model.Package;
import Model.User;
import client.Client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient extends Thread {
    private static Socket socket;
    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Listener listener = new Listener(socket);
            listener.start();

            User user = new User.UserBuilder().username("Pelle").password("kuken").build();

            Package package1 = new Package.PackageBuilder().type(1).sender(user).build();

            oos.writeObject(package1);
            oos.flush();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user2 = new User.UserBuilder().username("Bengt").password("kuken").build();
            package1 = new Package.PackageBuilder().type(1).sender(user2).build();
            oos.writeObject(package1);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static class Listener extends Thread {
        private static Socket socket;

        public Listener(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {
                while (true) {
                    Package aPackage = (Package) ois.readObject();
                    System.out.println(aPackage.getType());
                }
            }catch (IOException | ClassNotFoundException e) {
                System.err.println("Failure due to "+ e);
            }
        }
    }
}
