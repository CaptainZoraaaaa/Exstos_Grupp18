package com.example.exstos_grupp18;

import Model.Task;
import Sandbox.TestController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {

    @FXML
    private ImageView IMAGE;
    @FXML
    private ChoiceBox<String> activeStatus;
    @FXML
    private Button backToPreviousScreenButton;
    @FXML
    private ChoiceBox<String> chosenAssignees;
    @FXML
    private TextField creatorField;
    @FXML
    private TextField deadlineInputField;
    @FXML
    private Button editTaskButton;
    @FXML
    private CheckBox help;
    @FXML
    private TextArea taskCommentInputField;
    @FXML
    private TextArea taskDescriptionInputField;
    @FXML
    private TextField taskHeaderInputField;

    private LocalDate deadline;
    private TestController testController = new TestController();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String[] status = {"Archived", "Backlog", "In progress", "Waiting", "Done"};
    private String selectedStatus;
    private String user;
    private boolean flagged;

    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {
        //testController.editTask(Header, description, deadline, user, selectedStatus, creator, comment, flagged);

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
        Task task = new Task.TaskBuilder()
                .header("Exsto")
                .description("Alla är vi här")
                .estimatedTime("123")
                .comments("Bosse", "hej där.")
                .flaggedForHelp(true)
                .build();

        chosenAssignees.getItems().addAll(users);
        chosenAssignees.setOnAction(this::setUsers);
        activeStatus.getItems().addAll(status);
        activeStatus.setOnAction(this::setStatus);
    }
    public void setUsers(ActionEvent event){
        user = chosenAssignees.getValue();
    }
    public void setStatus(ActionEvent event){
        selectedStatus = activeStatus.getValue();
    }
}
