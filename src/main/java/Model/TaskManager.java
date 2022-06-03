package Model;

import java.time.LocalDate;

/**
 * THIS CLASS IS DEPRECATED AND IS NOT USED IN THE PRODUCT.
 * WE CHOSE TO KEEP THE CLASSES WE DO NOT USE FOR THEIR VALUE
 * AS ARTEFACTS.
 * THEREFORE, THIS CLASS IS NOT INCLUDED IN THE CLASS DIAGRAM
 */

/**
 * This class is used to handle operations for a task.
 * @author Christian Edvall
 */
public class TaskManager {
    private Task task;
    public TaskManager(){}
    /**
     * This method is used in order to create a task with it's incoming parameters. It does so by using the Task-class
     * builder.
     * @param header String containing header for the task.
     * @param description String containing description for the task.
     * @param estimatedTime String containing the estimated time for the task.
     * @param creator User object containing the creator for the task.
     * @param assignee User object containing the assignee for the task.
     * @param taskId int setting the task ID.
     */
    public Task createNewTask(String header, String description, LocalDate estimatedTime, String creator, String assignee, int taskId){
        task = new Task.TaskBuilder()
                .id(taskId)
                .header(header)
                .description(description)
                .deadline(estimatedTime)
                .creator(creator)
                .assignee(assignee)
                .currentStatus(Swimlane.Backlog)
                .flaggedForHelp(false)
                .build();
        return task;
    }

    /**
     * Method for adding assignee to a task.
     * @param assignee the assignee object meant to be added.
     * @param task Task object in order to manipulate the correct task.
     */
    public void addAssignee(String assignee, Task task){
        task.setAssignees(assignee);
    }
    /**
     * Method for changing a status for a task.
     * @param newLane Swimlane object used to assign status to a task.
     * @param task Task object in order to manipulate the correct task.
     */
    public void changeStatus(Swimlane newLane, Task task){
        task.setCurrentStatus(newLane);
    }

    /**
     * Method for flagging a task for help.
     * @param task task object to refer to the correct task.
     */
    public void flagForHelp(Task task){
        task.setFlaggedForHelp(true);
    }

    /**
     * Method for de-flagging task for help.
     * @param task task object to refer to the correct task.
     */
    public void removeHelpFlag(Task task){
        task.setFlaggedForHelp(false);
    }

    /**
     * Method for editing a task.
     * @param header String to set a new header.
     * @param task task object to refer to the correct task.
     */
    public void editHeader(String header, Task task){
        task.setHeader(header);
    }

    /**
     * Method for editing the description of a task.
     * @param description String to set new description.
     * @param task task object to refer to the correct task.
     */
    public void editDescription(String description, Task task){
        task.setDescription(description);
    }

    /**
     * Method for adding estimated time to a task.
     * @param estimatedTime String to set new/edit estimated time.
     * @param task task object to refer to the correct task.
     */
    public void editEstimatedTime(LocalDate estimatedTime, Task task){
        task.setDeadline(estimatedTime);
    }

    /**
     * Method for removing an assignee.
     * @param assignee User object to refer to the correct assignee.
     * @param task task object to refer to the correct task.
     */
    public void removeAssignee(User assignee, Task task){
        //Hur implementera?
        //task.setAssignees();
    }

    /**
     * Method for adding comment to a task.
     * @param comment Sting to set a comment to a task.
     * @param task task object to refer to the correct task.
     */
    public void addComment(String comment, Task task){
        task.setComments(comment);
    }
    // public void editComment(){}

    /**
     *
     * @param newSwimlane
     * @param task task object to refer to the correct task.
     */
    public void archieTask(Swimlane newSwimlane, Task task){
        task.setCurrentStatus(newSwimlane);
    }
    //Gör samma som setStatus?
    //public void returnFromArchive(){}
    //public void removeTask(){}
    
    
}
