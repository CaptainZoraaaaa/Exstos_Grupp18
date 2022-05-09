package com.example.exstos_grupp18;

import Model.Swimlane;
import Model.Task;
import controller.Controller;
import javafx.event.ActionEvent;
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

    private ArrayList<Node> nodesInProgress = new ArrayList<>();
    private boolean dropMenuVisible = false;
    private Controller controller = Controller.getInstance();
    private static KanbanViewController yes = new KanbanViewController();

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
    @FXML
    private Pane mainBarPane;

    public static KanbanViewController getInstance() {
        return yes;
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
        }
        else {
            dropMenuVisible = false;
        }

        myProjectsHbox.setVisible(dropMenuVisible);
        myProjectsHbox.setDisable(!dropMenuVisible); //set HBox with buttons
        mainBarPane.setVisible(dropMenuVisible);
        mainBarPane.setDisable(!dropMenuVisible); //set the pane for the progress bar
    }

    //TODO javadoca
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Task> current = controller.getTask();
        Node[] nodes = new Node[controller.getTaskSize()];
        for (int i = 0; i < controller.getTaskSize(); i++) {
            try {
                if (current.get(i).getCurrentStatus().equals(Swimlane.Backlog)) {
                    nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                    Node scene = nodes[i];
                    nodes[i].setId(String.valueOf(i));
                    //System.out.println(nodes[i].getId());
                    backlogList.getChildren().add(nodes[i]);
                }
                else if (current.get(i).getCurrentStatus().equals(Swimlane.InProgress)){
                    nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                    Node scene = nodes[i];
                    nodes[i].setId(String.valueOf(i));
                    //System.out.println(nodes[i].getId());
                    inProgressList.getChildren().add(nodes[i]);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void skriv(String scene) { //TODO testmetod tas bort när den är färdiganvänd
        for (int i = 0; i < backlogList.getChildren().size(); i++) {
            if (backlogList.getChildren().get(i).equals(scene)){
                System.out.println("här");
                inProgressList.getChildren().add(backlogList.getChildren().get(i));
                backlogList.getChildren().remove(i);
            }
        }
    }

    //TODO javadoca
    public void newTask(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newTask.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
