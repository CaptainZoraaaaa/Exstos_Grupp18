package com.example.exstos_grupp18;

import Model.Task;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Max Tiderman
 */
public class TaskController extends Thread implements Initializable {
    @FXML
    private Button printButton = new Button();
    @FXML
    private Label textField;
    @FXML
    private ImageView helpImage;
    @FXML
    private ChoiceBox<String> status;

    private String[] statusLog = {"Backlog", "Inprogress", "Review"};
    private String test; //Todo teststräng
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int taskId;
    private Controller controller = Controller.getInstance();

    /**
     * This method is for edit/previewing a task. This method will load the new scen aswell as send over the task that got pressed.
     * @param event -  event from Button on what task wanted to be edited used to get the right task to edit.
     * @throws IOException
     */
    @FXML
    void editTask(ActionEvent event) throws IOException { //todo javadoca
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditTask.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        ArrayList<Task> current = controller.getTask();
        for (int i = 0; i < current.size(); i++) {
            if (printButton.getParent().getId().equals(String.valueOf(current.get(i).getTask_id()))) {
                EditTaskController editTaskController = fxmlLoader.getController();
                editTaskController.loadedTask(current.get(i));
            }
        }
        scene = new Scene(root);
        stage.setScene(scene);
    }
    //Denna metod ska göra kallet till att flytta en task och sedan sätta denna tasks status till vilken swimlane den hamnar i.
    @FXML
    void dragTask(DragEvent dragEvent){

        dragEvent.setDropCompleted(true);
    }



    /**
     *This is a thread that starts when a new task is created. When the task loads in this thread will start and
     *compare this scenes id with the task object id if the task id match with the scene id the scene will then load the data from that object.
     * @author Max Tiderman & Christian Edvall.
     */
    @Override
    public void run() {
        ArrayList<Task> current = controller.getTask();
        for (int i = 0; i < current.size(); i++) {
            if (printButton.getParent().getId().equals(String.valueOf(current.get(i).getTask_id()))) {
                textField.setText(current.get(i).getHeader());
                printButton.setText("View task");
                helpImage.setVisible(false);
                if (current.get(i).isFlaggedForHelp()){
                    helpImage.setVisible(true);
                }
            }
        }
    }

    /**
     * This method Starts the Thread.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    } //todo javadoca

    /**
     * Test Method
     * @param event
     */
    public void setUsers(ActionEvent event){
        test = status.getValue();
    } //todo javadoca
}
