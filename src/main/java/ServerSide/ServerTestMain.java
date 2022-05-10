package ServerSide;

import Model.Project;
import Model.User;

import java.util.HashMap;

public class ServerTestMain {
    public static void main(String[] args) {
        HashMap<String, ClientHandler> clientMap = new HashMap<>();
        HashMap<String, User> userMap = new HashMap<>();
        HashMap<Integer, Project> projectMap = new HashMap<Integer, Project>();

        Server server = new Server();
        server.writeMapToFile(clientMap, "ClientSide");
        server.writeMapToFile(userMap, "user");
        server.writeMapToFile(projectMap, "project");

        System.out.println("All maps written");


    }
}
