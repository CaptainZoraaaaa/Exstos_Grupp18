package Model;

/**
 * This class is used to handle operations for a task.
 * @author Christian Edvall
 * @// TODO: 2022-04-19 Update class-diagram to match the code.
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
    public void createNewTask(String header, String description, String estimatedTime, User creator, User assignee, int taskId){
        task = new Task.TaskBuilder()
                .id(taskId)
                .header(header)
                .description(description)
                .estimatedTime(estimatedTime)
                .creator(creator)
                .assignee(assignee)
                .currentStatus(new Backlog())
                .flaggedForHelp(false)
                .build();
    }

    /**
     * Method for adding assignee to a task.
     * @param assignee the assignee object meant to be added.
     * @param task Task object in order to manipulate the correct task.
     */
    public void addAssignee(User assignee, Task task){
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
    public void flagForHelp(Task task){
        task.setFlaggedForHelp(true);
    }
    public void removeHelpFlag(Task task){
        task.setFlaggedForHelp(false);
    }
    public void editHeader(String header, Task task){
        task.setHeader(header);
    }
    public void editDescription(String description, Task task){
        task.setDescription(description);
    }
    public void editEstimatedTime(String estimatedTime, Task task){
        task.setEstimatedTime(estimatedTime);
    }
    public void removeAssignee(User assignee, Task task){
        //Hur implementera?
        //task.setAssignees();
    }
    public void addComment(String username, String comment, Task task){
        task.setComments(username, comment);
    }
    // gör samma som addComment just nu
    // public void editComment(){}
    public void archieTask(Swimlane newSwimlane, Task task){
        task.setCurrentStatus(newSwimlane);
    }
    //Gör samma som setStatus?
    //public void returnFromArchive(){}
    public void removeTask(){}
}
