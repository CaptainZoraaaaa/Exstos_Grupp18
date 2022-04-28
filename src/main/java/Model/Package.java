package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is used to package different objects in order to send multiple objects at once and redistribute them.
 * @author Christian Edvall
 */
public class Package {
    /**
     * The below static final ints are used as identifiers for what kind of objects the package contains.
     */
    public static final int USER = 0;
    public static final int TASK = 1;
    public static final int BOARD = 2;
    public static final int PROJECT = 3;
    public static final int MIXED = 4;

    private User sender;
    private ArrayList<Task> tasks;
    private Board board;
    private Project project;
    private LocalDateTime timeStamp;
    private int type;
    
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
        public PackageBuilder tasks(ArrayList<Task> tasks){
            aPackage.setTasks(tasks);
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
