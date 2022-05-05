package com.example.exstos_grupp18;

import Sandbox.TestController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    private LocalDate deadline;
    private TestController testController = new TestController();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String user;
    private boolean flagged;


    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }
    @FXML
    void createNewTask(ActionEvent event) {
        String header = taskHeaderInputField.getText();
        String description = taskDescriptionInputField.getText();
        String deadline = deadlineInputField.getText();
        String creator = creatorField.getText();
        String comment = taskCommentInputField.getText();
        testController.createTask(header, description, deadline, user, creator, comment, flagged);
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

