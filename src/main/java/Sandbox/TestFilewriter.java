package Sandbox;

import Model.Project;
import ServerSide.Server;
import ServerSide.ServerController;
import ServerSide.ServerFileManager;

import java.util.HashMap;

public class TestFilewriter {
    public static void main(String[] args) {
        HashMap<Integer, Project> projectMap = new HashMap<>();
        ServerFileManager.writeMapToFile(projectMap, "project");
        ServerFileManager.writeNewID(0, "project");
    }
}
