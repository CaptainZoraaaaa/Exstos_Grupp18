package Sandbox;

import Model.Package;
import Model.User;
import client.Client;

import java.io.IOException;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        TestClientModel client = new TestClientModel(new User(), "localhost", 8080);
        client.connect();

        Package package1 = new Package.PackageBuilder().type(1).build();
        client.sendUpdate(package1);


    }
}
