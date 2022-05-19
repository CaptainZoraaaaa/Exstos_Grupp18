package Model;


import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {

    private String username;
    private String password;
    private Image profilePicture;
    private HashMap<Project, Boolean> projects = new HashMap<>();
    private ArrayList<Task> myTasks = new ArrayList<>();

    private static int USERID = 0;

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

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public HashMap<Project, Boolean> getProjects() {
        return projects;
    }

    public void setProjects(HashMap<Project, Boolean> projects) {
        this.projects = projects;
    }

    public ArrayList<Task> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(ArrayList<Task> myTasks) {
        this.myTasks = myTasks;
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
         * This method is used to set a profile picture to the incoming Image value.
         * @param image an object of class Image with the users profile picture.
         * @return The method returns itself.
         */
        public UserBuilder image(Image image) {
            user.setProfilePicture(image);
            return this;
        }

        /**
         * This method is used to set the users projects to the incoming Hashmap.
         * @param projects a hashmap with the users projects.
         * @return The method returns itself.
         */
        public UserBuilder projects(HashMap<Project, Boolean> projects) {
            user.setProjects(projects);
            return this;
        }

        /**
         * This method is used to set the users tasks to the incoming Arraylist.
         * @param tasks an ArrayList with the users tasks.
         * @return The method returns itself.
         */
        public UserBuilder tasks(ArrayList<Task> tasks) {
            user.setMyTasks(tasks);
            return this;
        }

        public UserBuilder profilePicture(Image profilePicture) {
            user.setProfilePicture(profilePicture);
            return this;
        }
    }

}
