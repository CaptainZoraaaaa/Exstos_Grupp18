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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the Kanban view.
 * @author Anna Håkansson
 */
public class KanbanViewController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean dropMenuVisible = false;
    private Controller controller = Controller.getInstance();
    private static KanbanViewController kanbanViewController = new KanbanViewController();
    private String currentProjectName;
    private PopOver popOver = new PopOver();

    @FXML
    private ProgressBar backlogBar; //progress bar for backlog
    @FXML
    private VBox backlogList; //list for task objects in the backlog swimlane
    @FXML
    private ProgressBar doneBar; //progress bar for done
    @FXML
    private VBox doneList; //list for task objects in the done swimlane
    @FXML
    private ProgressBar dropMenuBar; //the progressbar in the drop down menu
    @FXML
    private ProgressBar inProgressBar; //the progress bar for in progress
    @FXML
    private VBox inProgressList; //list for task objects in the in progress swimlane
    @FXML
    private Button newTaskButton; //button for new task
    @FXML
    private ProgressBar waitingBar; //the progress bar for waiting
    @FXML
    private VBox waitingList; //list for task objects in the waiting swimlane
    @FXML
    private Label usernameLabel; //the label for the username
    @FXML
    private Pane mainBarPane;
    private double totalTasks = 1;

    public static KanbanViewController getInstance() {
        return kanbanViewController;
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
        mainBarPane.setVisible(!mainBarPane.isVisible());
         //set the pane for the progress bar
    }

    /**
     * This method is used for going back to the main menu.
     * @author Christian Edvall
     * @param event
     */
    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        HomePageController homePageController = fxmlLoader.getController();
        homePageController.setUpMySwimLane();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }
    /**
     *This method will get all of the current projects tasks lopp trought them and check their status.
     * Deppending on what status the task have the it will be placed in different VBOX's
     * That are the equivelant of the swimlanes.
     */
    @FXML
    void taskReleased(MouseEvent event){
        System.out.println(event.getSource());
        switch (event.getSource().toString()){
            case "VBox[id=backlogList]" -> controller.setLastSwimlanePosition(Swimlane.Backlog);
            case "VBox[id=inProgressList]" -> controller.setLastSwimlanePosition(Swimlane.InProgress);
            case "VBox[id=waitingList]" ->  controller.setLastSwimlanePosition(Swimlane.Waiting);
            case "VBox[id=doneList]" ->  controller.setLastSwimlanePosition(Swimlane.Done);
        }
    }
    //// TODO: 2022-06-03 javadoc. 
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(controller.getLoggedInUser());
        currentProjectName = controller.getActiveProject().getProjectName();
        ArrayList<Task> currentTaskList = controller.getTask();
        totalTasks = currentTaskList.size();
        DraggableTask draggableTask = new DraggableTask();
        Node[] nodes = new Node[controller.getTaskSize()];
        for (int i = 0; i < controller.getTaskSize(); i++) {
            try {
                if (currentTaskList.get(i).getCurrentStatus().equals(Swimlane.Backlog)) {
                    nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                    nodes[i].setId(String.valueOf(currentTaskList.get(i).getTask_id()));
                    //System.out.println(nodes[i].getId());
                    backlogList.getChildren().add(nodes[i]);
                    backlogList.setScaleZ(1);
                    nodes[i].setScaleZ(1000);
                }
                else if (currentTaskList.get(i).getCurrentStatus().equals(Swimlane.InProgress)){
                    nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                    nodes[i].setId(String.valueOf(currentTaskList.get(i).getTask_id()));
                    //System.out.println(nodes[i].getId());
                    inProgressList.getChildren().add(nodes[i]);
                }
                else if (currentTaskList.get(i).getCurrentStatus().equals(Swimlane.Waiting)){
                    nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                    nodes[i].setId(String.valueOf(currentTaskList.get(i).getTask_id()));
                    //System.out.println(nodes[i].getId());
                    waitingList.getChildren().add(nodes[i]);
                }
                else if (currentTaskList.get(i).getCurrentStatus().equals(Swimlane.Done)){
                    nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                    nodes[i].setId(String.valueOf(currentTaskList.get(i).getTask_id()));
                    //System.out.println(nodes[i].getId());
                    doneList.getChildren().add(nodes[i]);
                }
                draggableTask.makeDraggable(nodes[i]);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        backlogBar.setProgress(backlogList.getChildren().size() / totalTasks);
        inProgressBar.setProgress(inProgressList.getChildren().size() / totalTasks);
        waitingBar.setProgress(waitingList.getChildren().size() / totalTasks);
        doneBar.setProgress(doneList.getChildren().size() / totalTasks);

        LocalDate now = LocalDate.now();
        double daysLeft = Period.between(now,controller.getActiveProject().getDeadline()).getDays();//Dagar kavr tills slut datum
        double dayFromBeginning = Period.between(controller.getActiveProject().getCreatedDate(), controller.getActiveProject().getDeadline()).getDays(); // Dagar från början
        System.out.println(daysLeft);
        System.out.println(dayFromBeginning);
        if (daysLeft>0){
            this.dropMenuBar.setProgress(1-(daysLeft/dayFromBeginning));
        }
        else{
            this.dropMenuBar.setProgress(1);
        }
        usernameLabel.setText(controller.getLoggedInUser());
        mainBarPane.setVisible(false);

    }


    /**
     * Method to create new task sends the user to a new scene where the user can create the task
     * @param event - New Task button
     * @throws IOException
     */
    public void newTask(ActionEvent event) throws IOException {
        this.newTaskPopOver();
    }
    //// TODO: 2022-06-03 javadoc. 
    @FXML
    void editProject(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditProject.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
    //// TODO: 2022-06-03 javadoc. 
    public void setUserLabel(String text) {
        usernameLabel.setText(text);
    }
    //// TODO: 2022-06-03 javadoc. 
    @FXML
    void newTaskPopOver(){
        if(popOver == null || !popOver.isShowing()){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewTask.fxml"));
            Node newProjectRoot = null;
            try {
                newProjectRoot = fxmlLoader.load();
                NewTaskController newTaskController = fxmlLoader.getController();
                newTaskController.setKanbanViewController(this);
                popOver = new PopOver(newProjectRoot);
                popOver.setTitle("");
                popOver.setDetachable(false);
                popOver.setHeaderAlwaysVisible(false);
                popOver.show(newTaskButton, 330, 165);
                newTaskController.setPopOver(popOver);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //// TODO: 2022-06-03 javadoc. 
    @FXML
    void hideNewTaskPopOver(){
        popOver.hide();
    }
    //// TODO: 2022-06-03 javadoc.
    @FXML
    public void logOut(ActionEvent event) throws IOException {
        controller.logOut();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }
}
