package controller;

import Model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * @author Anna Håkansson
 *
 * last update 05-05-22
 *
 * Controller for the Kanban view.
 */
public class KanbanViewController {

    @FXML
    private ProgressBar backlogBar; //progress bar for backlog


    @FXML
    private ScrollPane backlogSwimlane; //the swimlane for backlog

    @FXML
    private ListView<Object> backlogList; //list for task objects in the backlog swimlane

    @FXML
    private Button calendarButton; //the calendar button in the drop down menu

    @FXML
    private Button currentProjectButton; //the current project button on the drop down menu

    @FXML
    private ProgressBar doneBar; //progress bar for done

    @FXML
    private ScrollPane doneSwimlane; //swimlane for done

    @FXML ListView<Object> doneList; //list for task objects in the done swimlane

    @FXML
    private ProgressBar dropMenuBar; //the progressbar in the drop down menu

    @FXML
    private Button helpButton; //the help button on the drop down menu

    @FXML
    private ProgressBar inProgressBar; //the progress bar for in progress

    @FXML
    private ScrollPane inProgressSwimlane; //the swimlane for in progress

    @FXML
    private ListView<Object> inProgressList; //list for task objects in the in progress swimlane

    @FXML
    private Button infoButton; //the info button on the drop down menu

    @FXML
    private Button kanbanButton; //the kanban button on the drop down menu

    @FXML
    private Button logOutButton; //the log out button

    @FXML
    private Button mainMenuButton; //main menu button

    @FXML
    private Button myProjectsButton; //"my projects button (which shows the drop down menu)

    @FXML
    private HBox myProjectsHbox; //the Hbox for the buttons in the drop down menu

    @FXML
    private Pane myProjectsMenu; //the pane the drop down menu is on.

    @FXML
    private Button newTaskButton; //button for new task

    @FXML
    private Button personalViewButton; //button for personal view

    @FXML
    private Button settingsButton; //settings-button on drop down menu

    @FXML
    private ProgressBar waitingBar; //the progress bar for waiting

    @FXML
    private ScrollPane waitingSwimlane; //the swimlane for waiting

    @FXML
    private ListView<Object> waitingList; //list for task objects in the waiting swimlane

    @FXML
    private Label usernameLabel; //the label for the username

    @FXML
    private Pane mainBarPane;

    private boolean dropMenuVisible = false;

    private ArrayList<Task> backlogTasks = new ArrayList<>();
    private ArrayList<Task> inProgressTasks = new ArrayList<>();
    private ArrayList<Task> waitingTasks = new ArrayList<>();
    private ArrayList<Task> doneTasks = new ArrayList<>();

    /**
     * @author Anna Håkansson
     * @param username logged in user
     *
     * Method for setting the username label to the logged in users name.
     */
    public void setUsername(String username) {
     usernameLabel.setText(username);
    }

    /**
     * @author Anna Håkansson
     *
     * Method that is called upon when the "My projects" button is pressed.
     * boolean dropMenuVisible keeps track if the menu should be shown or
     * diseapeared when the button is pressed.
     */
    public void myProjectsPressed() {
        if(!dropMenuVisible) {
            dropMenuVisible = true;
        } else {
            dropMenuVisible = false;
        }
        myProjectsHbox.setVisible(dropMenuVisible);
        myProjectsHbox.setDisable(!dropMenuVisible); //set HBox with buttons
        mainBarPane.setVisible(dropMenuVisible);
        mainBarPane.setDisable(!dropMenuVisible); //set the pane for the progress bar
    }

    /**
     * @author Anna Håkansson
     * @param taskList list of tasks in a project
     *
     * Method for sorting tasks in to different lists
     * depending on their set status
     */
    public void sortTasks(ArrayList<Task> taskList) {
        if (taskList != null) { //if taskList isnt null
            for(Task task : taskList) { //for each task
                if (task != null) { //if task isnt null
                    Swimlane status = task.getCurrentStatus(); //get the status from the task
                    if(status instanceof Backlog) {
                        backlogTasks.add(task);
                    }
                    else if(status instanceof InProgress) {
                        inProgressTasks.add(task);
                    }
                    else if(status instanceof Waiting) {
                        waitingTasks.add(task);
                    }
                    else if(status instanceof Done) {
                        doneTasks.add(task);
                    }
                }
            }
        }
    }

    public void displayTasks() {
        for(Task task : backlogTasks) {
            FlowPane flowPane = formatTask(task);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    backlogList.getItems().add(flowPane);
                }
            });
        }
    }

    public FlowPane formatTask(Task task) {
        FlowPane flowPane = new FlowPane();
        flowPane.setStyle("-fx-background-color:  #f8f7f4;");

        Text header = new Text(task.getHeader());
        header.setFont(Font.font("Roboto"));
        flowPane.getChildren().add(header);

        Circle circle = new Circle();
        circle.setStyle("-fx-background-color: #FDDA0D");
        circle.setVisible(task.isFlaggedForHelp());
        flowPane.getChildren().add(circle);

        Text description = new Text(task.getDescription());
        description.setFont(Font.font("Roboto"));
        flowPane.getChildren().add(description);

        return flowPane;
    }

    public void testMethod() {
        TaskManager taskManager = new TaskManager();
        Task task1 = taskManager.createNewTask("Test1", "Coolers pistolers", "1", new User(), null, 1);
        task1.setCurrentStatus(new Backlog());
        task1.setFlaggedForHelp(false);
        Task task2 = taskManager.createNewTask("Test2", "Coolers pistolers", "1", new User(), null, 2);
        task2.setCurrentStatus(new Backlog());
        task2.setFlaggedForHelp(true);
        Task task3 = taskManager.createNewTask("Test3", "Coolers pistolers", "1", new User(), null, 3);

        ArrayList<Task> tasklist = new ArrayList<>();
        tasklist.add(task1);
        tasklist.add(task2);
        tasklist.add(task3);

        sortTasks(tasklist);
        displayTasks();
    }


}
