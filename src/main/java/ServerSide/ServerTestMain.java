package ServerSide;

import Model.Project;
import Model.User;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class ServerTestMain {
    public static void main(String[] args) {
        HashMap<String, ClientHandler> clientMap = new HashMap<>();
        HashMap<String, User> userMap = new HashMap<>();
        HashMap<Integer, Project> projectMap = new HashMap<Integer, Project>();

        Server server = new Server();
        server.writeMapToFile(clientMap, "client");
        server.writeMapToFile(userMap, "user");
        server.writeMapToFile(projectMap, "project");

        User user = new User.UserBuilder().username("Pelle").password("kuken").build();
        server.addUser(user);

        System.out.println("All maps written");


    }
}
