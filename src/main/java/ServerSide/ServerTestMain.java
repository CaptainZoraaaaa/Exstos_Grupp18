package ServerSide;

import Model.Project;
import Model.User;

/*
* TODO: ¨¨¨¨¨¨¨¨¨¨¨¨¨ servern ska svara och skicka tillbaka
*  todo skriva till fil på lämpliga ställen 
*/

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

        ServerController serverController = new ServerController();

        User user = new User.UserBuilder().username("Pelle").password("kuken").build();
        serverController.addUser(user);


    }
}
