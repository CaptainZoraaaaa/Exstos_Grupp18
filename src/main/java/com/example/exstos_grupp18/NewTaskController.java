package com.example.exstos_grupp18;

import Model.ArchivedTasks;
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
    @FXML
    private ChoiceBox<String> activeStatus;

    private LocalDate deadline;
    private TestController testController = new TestController();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String[] status = {"Archived", "Backlog", "In progress", "Waiting", "Done"};
    private String selectedStatus;
    private String user;
    private boolean flagged;

    /**
     * Method for returning to previous screen.
     * // TODO: 2022-05-06 add change scene to this method body.
     * @param event triggered by clicking the button backToPreviousScreenButton.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }

    /**
     * Method for creating new task, at the moment the method is connected to TestController.
     * @param event upon clicking the button Create task this method will run.
     */
    @FXML
    void createNewTask(ActionEvent event) {
        String header = taskHeaderInputField.getText();
        String description = taskDescriptionInputField.getText();
        String deadline = deadlineInputField.getText();
        String creator = creatorField.getText();
        String comment = taskCommentInputField.getText();
        testController.createTask(header, description, deadline, user, selectedStatus, creator, comment, flagged);
    }

    /**
     * Method for setting the boolean value flagged to true och done, determined if the box is checked or not. If it's
     * checked boolean will be set to true, otherwise false.
     * @param event triggered by clicking on the checkbox for flag for help.
     */
    @FXML
    void flagForHelp(ActionEvent event) {
        if(help.isSelected()){
            flagged = true;
        } else {
            flagged = false;
        }
    }

    /**
     * Method for intializing the task ChoiceBoxes with users to select as assignees and status as statuses.
     * @param url url.
     * @param resourceBundle resourceBundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       chosenAssignees.getItems().addAll(users);
       chosenAssignees.setOnAction(this::setUsers);
       activeStatus.getItems().addAll(status);
       activeStatus.setOnAction(this::setStatus);
    }

    /**
     * Method for setting the values in the list.
     * @param event Event is triggered by the above this::setUsers.
     */
    public void setUsers(ActionEvent event){
        user = chosenAssignees.getValue();
    }

    /**
     * Method for setting the values in the list.
     * @param event Event is triggered by the above this::setStatus.
     */
    public void setStatus(ActionEvent event){
        selectedStatus = activeStatus.getValue();
    }
}

