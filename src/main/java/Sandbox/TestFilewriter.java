package Sandbox;

import Model.Project;
import Model.User;
import ServerSide.Server;
import ServerSide.ServerController;
import ServerSide.ServerFileManager;

import java.util.HashMap;

public class TestFilewriter {
    public static void main(String[] args) {
        ServerFileManager sfm = new ServerFileManager();
        HashMap<Integer, Project> projectMap = new HashMap<>();
        HashMap<String, User> userMap = new HashMap<>();
        ServerFileManager.writeMapToFile(projectMap, "project");
        ServerFileManager.writeMapToFile(userMap, "user");
        ServerFileManager.writeNewID(0, "project");
        ServerFileManager.writeNewID(0, "task");
    }
}
