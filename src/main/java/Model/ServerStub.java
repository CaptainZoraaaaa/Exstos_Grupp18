package Model;

import java.util.ArrayList;

/**
 * @author Anna Håkansson
 * Last update: 2022-04-19
 *
 * This class is a temporary replacement for the server
 */
public class ServerStub {
    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public ServerStub() {
        setUp();
    }

    private void setUp() {
        UserManager userManager = new UserManager();
        users.add(userManager.createNewUser("Pelle", "123", null));
        users.add(userManager.createNewUser("Kalle", "password01", null));
        users.add(userManager.createNewUser("Olle", "hejhej", null));
        System.out.println("setting up");
    }

    /**
     * @author Anna Håkansson
     *
     * @param username the username to be checked
     * @return if the given username is unique
     *
     * Temporary method for checking if a username is unique. Returns true if
     * it doesn't already exist in the server and false if it does.
     */
    public boolean checkUsername(String username) {
        System.out.println(users.size());
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @author Anna Håkansson
     *
     * @param username the username used to login
     * @param password the password used to login
     * @return if username and password was correct or not
     *
     * Temporary method for checking login credentials by matching username with
     * signed up users and then matching the password.
     */
    public boolean loginCheck(String username, String password) {
        UserManager userManager = new UserManager();
        users.add(userManager.createNewUser("Pelle", "123", null));
        users.add(userManager.createNewUser("Kalle", "password01", null));
        users.add(userManager.createNewUser("Olle", "hejhej", null));
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i) != null) {
                if (users.get(i).getUsername().equals(username)) {
                    if (users.get(i).getPassword().equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
