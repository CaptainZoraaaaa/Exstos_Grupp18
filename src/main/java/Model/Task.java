package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class represents a task in Exsto and all of it's instance variables have their own getters and setters.
 * @author Christian Edvall
 */
public class Task implements Serializable {
    private String header;
    private String description;
    private LocalDate deadline;
    private Swimlane currentStatus;
    private ArrayList<String> assignees = new ArrayList<>();
    private boolean flaggedForHelp;
    private String creator;
    private LinkedList<String> comments = new LinkedList<>();
    private int task_id;

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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
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

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int id) {
        this.task_id = id;
    }

    /**
     * This is Tasks inner class, this class is a builder and is used in order to create new tasks.
     *
     * @author Christian Edvall
     */
    public static class TaskBuilder {
        private final Task task = new Task();

        /**
         * This method is used in order to return the built task.
         *
         * @return a task.
         */
        public Task build() {
            return task;
        }

        /**
         * This method is used to set a task header to the incoming int value.
         *
         * @param id int
         * @return the method returns itself.
         */
        public TaskBuilder id(int id) {
            task.setTask_id(id);
            return this;
        }

        /**
         * This method is used to set a task header to the incoming String value.
         *
         * @param header a String with a header name.
         * @return The method returns itself.
         */
        public TaskBuilder header(String header) {
            task.setHeader(header);
            return this;
        }

        /**
         * This method is used to set a task description to the incoming String value.
         *
         * @param description a String with a description.
         * @return The method returns itself.
         */
        public TaskBuilder description(String description) {
            task.setDescription(description);
            return this;
        }

        /**
         * This method is used to set a task estimated time to the incoming String value.
         *
         * @param time String with a time.
         * @return The method returns itself.
         */
        public TaskBuilder deadline(LocalDate time) {
            task.setDeadline(time);
            return this;
        }

        /**
         * This method is used to set a tasks current status using a refence to the Class Svimlane.
         *
         * @param currentStatus An object of the Class Swimlane, containing a status.
         * @return The method returns itself.
         */
        public TaskBuilder currentStatus(Swimlane currentStatus) {
            //Ej färdig.
            task.setCurrentStatus(currentStatus);
            return this;
        }

        /**
         * This method adds who are assigned to a task.
         *
         * @param assignee user object.
         * @return The method returns itself.
         */
        public TaskBuilder assignee(String assignee) {
            task.setAssignees(assignee);
            return this;
        }

        /**
         * This method is used to flag is help is needed or not using a boolean.
         *
         * @param flag Is a boolean.
         * @return The method returns itself.
         */
        public TaskBuilder flaggedForHelp(boolean flag) {
            task.setFlaggedForHelp(flag);
            return this;
        }

        /**
         * This method is used to set who the creator for a task is.
         *
         * @param creator A User object.
         * @return The method returns itself.
         */
        public TaskBuilder creator(String creator) {
            task.setCreator(creator);
            return this;
        }

        /**
         * This method is used to set a list of comments to a task.
         *
         * @param comment String of comment.
         * @return The method returns itself.
         */
        public TaskBuilder comments(String comment) {
            task.setComments(comment);
            return this;
        }
    }
}
