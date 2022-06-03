package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is used to package different objects in order to send multiple objects at once and redistribute them.
 * @author Christian Edvall & Anna Håkansson
 */
public class DataPackage implements Serializable {
    /**
     * The below static final ints are used as identifiers for what kind of objects the package contains.
     */
    public static final int USER_LOGGED_IN = 0; //user object (sender)
    public static final int NEW_USER_REGISTRATION = 1; //user object (sender)
    public static final int USER_ASSIGNED_TO_PROJECT = 2; //String username & project object
    public static final int USER_REMOVED_FROM_PROJECT = 3; //user & project object (sender)
    public static final int USER_DELETED = 4; //user object (sender)
    public static final int USER_LOGGED_OUT = 5; //user object (sender)
    public static final int NEW_TASK = 6; //task & project object
    public static final int TASK_EDITED = 7; //task & project object
    public static final int TASK_REMOVED = 8; //task & project object
    public static final int NEW_PROJECT = 9; //project object
    public static final int PROJECT_EDITED = 10; //project object
    public static final int PROJECT_REMOVED = 11; //project object

    public static final int LOGIN_VERIFICATION = 12; //boolean & user & project arraylist (from server)
    public static final int REGISTRATION_VERIFICATION = 13; //boolean (from server)
    public static final int PROJECT_UPDATE = 14; //project object and Task[] object (from server)

    private User sender;
    private Task task;
    private Project project;
    private String username;
    private boolean verificationSuccess;
    private int packageType;
    private String password;
    private User userFromServer;
    private ArrayList<Project> projectList;
    private Task[] tasks;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getPackageType(){
        return packageType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isVerificationSuccess() {
        return verificationSuccess;
    }

    public void setVerificationSuccess(boolean verificationSuccess) {
        this.verificationSuccess = verificationSuccess;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUserFromServer() {
        return userFromServer;
    }

    public void setUserFromServer(User userFromServer) {
        this.userFromServer = userFromServer;
    }

    public ArrayList<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList<Project> projectList) {
        this.projectList = projectList;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    /**
     * Inner builder class for packages.
     */
    public static class PackageBuilder{
        private final DataPackage aDataPackage = new DataPackage();

        /**
         * Upon call this method will finish building a package and return it.
         * @return returns a DataPackage object.
         */
        public DataPackage build(){
            return aDataPackage;
        }

        /**
         * This method is used to track the user who sent a object but can also be used to send a user to be saved
         * offline.
         * @param sender A user object.
         * @return returns itself.
         */
        public PackageBuilder sender(User sender){
            aDataPackage.setSender(sender);
            return this;
        }

        /**
         * @author Anna Håkansson
         * @param password to be set
         * @return this
         */
        public PackageBuilder password(String password){
            aDataPackage.setPassword(password);
            return this;
        }

        /**
         * This method is used to send a project.
         * @param project a Project object.
         * @return returns itself.
         */
        public PackageBuilder project(Project project){
            aDataPackage.setProject(project);
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
            aDataPackage.setUsername(username);
            return this;
        }

        /**
         * @author Anna Håkansson
         * @param projectList
         * @return
         */
        public PackageBuilder projectList(ArrayList<Project> projectList) {
            aDataPackage.setProjectList(projectList);
            return this;
        }

        /**
         * @author Anna Håkansson
         * This method is used when the server need to tell
         * a client if a request went ok.
         *
         * @param verificationSuccess if the request was a success
         * @return itself
         */
        public PackageBuilder verificationSuccess(boolean verificationSuccess) {
            aDataPackage.setVerificationSuccess(verificationSuccess);
            return this;
        }
        /**
         * This method is used to send or set a list of tasks containing one or more tasks.
         * @param task a task.
         * @return returns itself.
         */
        public PackageBuilder task(Task task){
            aDataPackage.setTask(task);
            return this;
        }

        /**
         * @author Anna Håkansson
         * @param tasks
         * @return
         */
        public PackageBuilder taskList(Task[] tasks){
            aDataPackage.setTasks(tasks);
            return this;
        }

        /**
         * @author Anna Håkansson
         * @param userFromServer user object from
         * @return this
         */
        public PackageBuilder userFromServer(User userFromServer){
            aDataPackage.setUserFromServer(userFromServer);
            return this;
        }

        /**
         * This method is used to set what type of DataPackage the built package is.
         * @param packageType an integer that sets what type of package is being sent.
         * @return returns itself.
         */
        public PackageBuilder packageType(int packageType){
            aDataPackage.packageType = packageType;
            return this;
        }
    }
}
