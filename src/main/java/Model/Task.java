package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class represents a task in Exsto and all of it's instance variables have their own getters and setters.
 * @author Christian Edvall
 * @version 1.0
 */
public class Task {
    private String header;
    private String description;
    private LocalDate estimatedTime;
    private Swimlane currentStatus;
    private ArrayList<String> assignees = new ArrayList<>();
    private ArrayList<Task> dependencies  = new ArrayList<>();
    private boolean flaggedForHelp;
    private String creator;
    private LinkedList<String> comments = new LinkedList<>(); // String 1 = username, String 2 = comment.
    private int TASK_ID;

    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getEstimatedTime() {
        return estimatedTime;
    }
    public void setEstimatedTime(LocalDate estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
    public Swimlane getCurrentStatus() {
        return currentStatus;
    }
    public void setCurrentStatus(Swimlane currentStatus) {
        this.currentStatus = currentStatus;
    }
    public ArrayList<String> getAssignees() {
        return assignees;
    }
    public void setAssignees(String assignee) {
        assignees.add(assignee);
    }
    public ArrayList<Task> getDependencies() {
        return dependencies;
    }
    public void setDependencies(ArrayList<Task> dependencies) {
        this.dependencies = dependencies;
    }
    public boolean isFlaggedForHelp() {
        return flaggedForHelp;
    }
    public void setFlaggedForHelp(boolean flaggedForHelp) {
        this.flaggedForHelp = flaggedForHelp;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public LinkedList<String> getComments() {
        return comments;
    }
    public void setComments(String comment) {
        comments.add(comment);
    }
    //Osäker kring hur implementation av task ID ska fungera så lämnar detta tillsvidare.
    public int getTASK_ID(){
        return TASK_ID;
    }
    public void setTASK_ID(int id){
        this.TASK_ID = id;
    }

    /**
     * This is Tasks inner class, this class is a builder and is used in order to create new tasks.
     * @author Christian Edvall
     * @version 1.0
     */
    public static class TaskBuilder{
        private final Task task = new Task();

        /**
         * This method is used in order to return the built task.
         * @return a task.
         */
        public Task build(){
            return task;
        }

        /**
         * This method is used to set a task header to the incoming int value.
         * @param id int
         * @return the method returns itself.
         */
        public TaskBuilder id(int id){
            task.setTASK_ID(id);
            return this;
        }

        /**
         * This method is used to set a task header to the incoming String value.
         * @param header a String with a header name.
         * @return The method returns itself.
         */
        public TaskBuilder header(String header){
            task.setHeader(header);
            return this;
        }

        /**
         * This method is used to set a task description to the incoming String value.
         * @param description a String with a description.
         * @return The method returns itself. 
         */
        public TaskBuilder description(String description){
            task.setDescription(description);
            return this;
        }

        /**
         * This method is used to set a task estimated time to the incoming String value.
         * @param time String with a time.
         * @return The method returns itself.
         */
        public TaskBuilder estimatedTime(LocalDate time){
            task.setEstimatedTime(time);
            return this;
        }

        /**
         * This method is used to set a tasks current status using a refence to the Class Svimlane.
         * @param currentStatus An object of the Class Swimlane, containing a status.
         * @return The method returns itself.
         */
        public TaskBuilder currentStatus(Swimlane currentStatus){
            //Ej färdig.
            task.setCurrentStatus(currentStatus);
            return this;
        }

        /**
         * This method adds who are assigned to a task.
         * @param assignee user object.
         * @return The method returns itself. 
         */
        public TaskBuilder assignee(String assignee){
            task.setAssignees(assignee);
            return this;
        }

        /**
         * This method adds what tasks the task created are dependent on.
         * @param dependency Is an ArrayList of tasks.
         * @return The method returns itself. 
         */
        public TaskBuilder dependency(ArrayList<Task> dependency){
            task.setDependencies(dependency);
            return this;
        }

        /**
         * This method is used to flag is help is needed or not using a boolean.
         * @param flag Is a boolean.
         * @return The method returns itself. 
         */
        public TaskBuilder flaggedForHelp(boolean flag){
            task.setFlaggedForHelp(flag);
            return this;
        }

        /**
         * This method is used to set who the creator for a task is.
         * @param creator A User object.
         * @return The method returns itself. 
         */
        public TaskBuilder creator(String creator){
            task.setCreator(creator);
            return this;
        }

        /**
         * This method is used to set a list of comments to a task.
         * @param comment String of comment.
         * @return The method returns itself. 
         */
        public TaskBuilder comments(String comment){
            task.setComments(comment);
            return this;
        }
    }
}
