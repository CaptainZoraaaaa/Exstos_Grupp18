package com.example.exstos_grupp18;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Anna Håkansson
 *
 * last update 05-05-22
 *
 * Controller for the Kanban view.
 */
public class KanbanViewController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ProgressBar backlogBar; //progress bar for backlog

    @FXML
    private ScrollPane backlogSwimlane; //the swimlane for backlog

    @FXML
    private VBox backlogList; //list for task objects in the backlog swimlane

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
    private VBox inProgressList; //list for task objects in the in progress swimlane

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
    private ArrayList<Node> nodesInProgress = new ArrayList<>();

    private boolean dropMenuVisible = false;

    private static KanbanViewController yes = new KanbanViewController();

    public KanbanViewController getInstance() {
        return this;
    }

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Node[] nodes = new Node[10];
        for (int i = 0; i < 10; i++) {
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                backlogList.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void skriv(Parent scene) {
        for (int i = 0; i < backlogList.getChildren().size(); i++) {
            if (backlogList.getChildren().get(i).equals(scene)){
                System.out.println("här");
                inProgressList.getChildren().add(backlogList.getChildren().get(i));
                backlogList.getChildren().remove(i);
            }
        }
    }
}
