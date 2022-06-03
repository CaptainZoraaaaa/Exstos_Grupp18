package Model;


import java.io.Serializable;
import java.util.HashMap;

/**
 * Class that's holds users with its projects and tasks
 * @author Linnéa Flystam
 *
 */
public class User implements Serializable {

    private String username;
    private String password;
    private HashMap<Integer, Project> projects = new HashMap<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<Integer, Project> getProjects() {
        return projects;
    }

    public void setProjects(HashMap<Integer, Project> projects) {
        this.projects = projects;
    }


    /**
     * Builder-class to create new users.
     * @author Linnéa Flystam
     */
    public static class UserBuilder {

        private User user = new User();

        /**
         * This method is used in order to return the built user.
         * @return a user.
         */
        public User build() {
            return user;
        }

        /**
         * This method is used to set a username to the incoming String value.
         * @param username a String with the users username.
         * @return The method returns itself.
         */
        public UserBuilder username(String username) {
            user.setUsername(username);
            return this;
        }

        /**
         * This method is used to set a password to the incoming String value.
         * @param password a String with the users password.
         * @return The method returns itself.
         */
        public UserBuilder password(String password) {
            user.setPassword(password);
            return this;
        }
        /**
         * This method is used to set the users projects to the incoming Hashmap.
         * @param projects a hashmap with the users projects.
         * @return The method returns itself.
         */
        public UserBuilder projects(HashMap<Integer, Project> projects) {
            user.setProjects(projects);
            return this;
        }
    }

}
