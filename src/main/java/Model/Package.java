package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is used to package different objects in order to send multiple objects at once and redistribute them.
 * @author Christian Edvall
 */
public class Package implements Serializable {
    /**
     * The below static final ints are used as identifiers for what kind of objects the package contains.
     */
    public static final int USER_LOGGED_IN = 0; //user object (sender is enough)
    public static final int NEW_USER_REGISTRATION = 1; //user object
    public static final int USER_ASSIGNED_TO_PROJECT = 2; //String username & project object
    public static final int USER_REMOVED_FROM_PROJECT = 3; //user & project object
    public static final int USER_DELETED = 4; //user object
    public static final int USER_LOGGED_OUT = 5; //user object
    public static final int NEW_TASK = 6; //task & project object
    public static final int TASK_EDITED = 7; //task & project object
    public static final int TASK_REMOVED = 8; //task & project object
    public static final int NEW_PROJECT = 9; //project object
    public static final int PROJECT_EDITED = 10; //project object
    public static final int PROJECT_REMOVED = 11; //project object

    public static final int LOGIN_VERIFICATION = 12; //boolean (from server)
    public static final int REGISTRATION_VERIFICATION = 13; //boolean (from server)
    public static final int PROJECT_UPDATE = 14;

    private User sender;
    private ArrayList<Task> tasks;
    private Board board;
    private Project project;
    private LocalDateTime timeStamp;
    private String username;
    private boolean OK;
    private int type;
    private String password;

    public Package() {
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDateTime getTimeStamp(){
        return timeStamp;
    }

    public void setLocalDateTime(LocalDateTime timeStamp){
        this.timeStamp = timeStamp;
    }
    public int getType(){
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOK() {
        return OK;
    }

    public void setOK(boolean OK) {
        this.OK = OK;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Inner builder class for packages.
     */
    public static class PackageBuilder{
        private final Package aPackage = new Package();

        /**
         * Upon call this method will finish building a package and return it.
         * @return returns a Package object.
         */
        public Package build(){
            return aPackage;
        }

        /**
         * This method is used to track the user who sent a object but can also be used to send a user to be saved
         * offline.
         * @param sender A user object.
         * @return returns itself.
         */
        public PackageBuilder sender(User sender){
            aPackage.setSender(sender);
            return this;
        }

        /**
         * This method is used to send or set a list of tasks containing one or more tasks.
         * @param tasks An ArrayList of tasks.
         * @return returns itself.
         */
        public PackageBuilder password(String password){
            aPackage.setPassword(password);
            return this;
        }

        /**
         * This method is used to send the boards pf a project.
         * @param board a Board object.
         * @return returns itself.
         */
        public PackageBuilder board(Board board){
            aPackage.setBoard(board);
            return this;
        }

        /**
         * This method is used to send a project.
         * @param project a Project object.
         * @return returns itself.
         */
        public PackageBuilder project(Project project){
            aPackage.setProject(project);
            return this;
        }

        /**
         * @author Anna Håkansson
         * This method is used to send the username of
         * a user you want to add to a project
         *
         * @param username for the person to be added
         * @return itself
         */
        public PackageBuilder username(String username) {
            aPackage.setUsername(username);
            return this;
        }

        /**
         * @author Anna Håkansson
         * This method is used when the server need to tell
         * a client if a request went ok.
         *
         * @param ok if the request was a success
         * @return itself
         */
        public PackageBuilder ok(boolean ok) {
            aPackage.setOK(ok);
            return this;
        }

        public PackageBuilder tasks(ArrayList<Task> tasks){
            aPackage.setTasks(tasks);
            return this;
        }

        /**
         * This method is used to set what type of Package the built package is. 
         * @param type an integer that sets what type of package is being sent.
         * @return returns itself.
         */
        public PackageBuilder type(int type){
            aPackage.type = type;
            return this;
        }
    }
}
