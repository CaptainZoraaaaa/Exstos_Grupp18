package Sandbox;

import Model.Package;
import Model.User;
import client.Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            User user = new User.UserBuilder().username("Pelle").password("kuken").build();

            Package package1 = new Package.PackageBuilder().type(1).sender(user).build();

            oos.writeObject(package1);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
