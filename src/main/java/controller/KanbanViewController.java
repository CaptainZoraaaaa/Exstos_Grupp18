package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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

    private boolean dropMenuVisible = false;

    /**
     * @author Anna Håkansson
     * @param username logged in user
     *
     * Method for setting the username label to the logged in users name.
     */
    public void setUsername(String username) {
     usernameLabel.setText(username);
    }

    public void myProjectsPressed() {
        if(!dropMenuVisible) {
            dropMenuVisible = true;
        } else {
            dropMenuVisible = false;
        }
        myProjectsMenu.setVisible(dropMenuVisible);
        myProjectsMenu.setDisable(!dropMenuVisible);
    }



}
