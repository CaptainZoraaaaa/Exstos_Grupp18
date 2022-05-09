package com.example.exstos_grupp18;

import Model.Swimlane;
import Model.Task;
import Sandbox.TestController;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NewTaskController implements Initializable {

    @FXML
    private Button backToPreviousScreenButton;
    @FXML
    private ChoiceBox<String> chosenAssignees;
    @FXML
    private Button createNewTaskButton;
    @FXML
    private TextField creatorField;
    @FXML
    private CheckBox help;
    @FXML
    private TextArea taskCommentInputField;
    @FXML
    private TextField deadlineInputField;
    @FXML
    private TextArea taskDescriptionInputField;
    @FXML
    private TextField taskHeaderInputField;
    public int counter;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private LocalDate deadline;
    private Controller testController = Controller.getInstance();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String user;
    private boolean flagged;


    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }
    @FXML
    void createNewTask(ActionEvent event) throws IOException {
        String header = taskHeaderInputField.getText();
        String description = taskDescriptionInputField.getText();
        String deadline = deadlineInputField.getText();
        String creator = creatorField.getText();
        String comment = taskCommentInputField.getText();
        Task task = new Task.TaskBuilder().header(header).description(description).estimatedTime(deadline).currentStatus(Swimlane.InProgress).id(testController.getTaskSize()).build();
        testController.createTask(task);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }
    @FXML
    void flagForHelp(ActionEvent event) {
        if(help.isSelected()){
            flagged = true;
        } else {
            flagged = false;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       chosenAssignees.getItems().addAll(users);
       chosenAssignees.setOnAction(this::setUsers);
    }
    public void setUsers(ActionEvent event){
        user = chosenAssignees.getValue();
    }
}

