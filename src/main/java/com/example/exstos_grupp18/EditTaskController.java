package com.example.exstos_grupp18;

import Model.Task;
import Sandbox.TestController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Christian Edvall
 */
public class EditTaskController implements Initializable {

    @FXML
    private ImageView background; //todo se över om vi ska ta bort
    @FXML
    private ChoiceBox<String> statusList;
    @FXML
    private ChoiceBox<String> assigneeList;
    @FXML
    private TextField creatorField;
    @FXML
    private DatePicker taskDeadlineDate;
    @FXML
    private CheckBox helpBox;
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
    private String selectedUser;
    private boolean flagged;

    /**
     * Method for returning to previous screen.
     * TODO: 2022-05-06 add change scene to this method body, dependent on the main menu.
     * @param event triggered by clicking the button backToPreviousScreenButton.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }

    /**
     * Method for saving changes in a task, at the moment this is connected to the TestController.
     * @param event
     */
    @FXML
    void saveChanges(ActionEvent event) {
        //testController.editTask(Header, description, deadline, user, selectedStatus, creator, comment, flagged);
    }

    /**
     * Method for setting the boolean value flagged to true och done, determined if the box is checked or not. If it's
     * checked boolean will be set to true, otherwise false.
     * @param event triggered by clicking on the checkbox for flag for help.
     */
    @FXML
    void flagForHelp(ActionEvent event) {
        if(helpBox.isSelected()){
            flagged = true;
        }
        else {
            flagged = false;
        }
    }
    @FXML
    void setTaskToEditable(ActionEvent event){
        taskHeaderInputField.setEditable(true);
        taskDescriptionInputField.setEditable(true);
        taskDeadlineDate.setDisable(false);
        assigneeList.setDisable(false);
        statusList.setDisable(false);
        taskCommentInputField.setEditable(true);
        helpBox.setDisable(false);
    }
    @FXML
    void chooseDate(ActionEvent event){
        deadline = taskDeadlineDate.getValue();

    }

    /**
     * Method for intializing the task ChoiceBoxes with users to select as assignees and status as statuses.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //todo lägg till kommentarer i metodkropp
        Task task = new Task.TaskBuilder()
                .header("Exsto")
                .description("Alla är vi här")
                .estimatedTime("123")
                .comments("Bosse", "hej där.")
                .flaggedForHelp(true)
                .build();

        assigneeList.getItems().addAll(users);
        assigneeList.setOnAction(this::setUsers);
        statusList.getItems().addAll(status);
        statusList.setOnAction(this::setStatus);
    }

    /**
     * Method for setting the values in the list.
     * @param event Event is triggered by the above this::setUsers.
     */
    public void setUsers(ActionEvent event){
        selectedUser = assigneeList.getValue();
    }

    /**
     * Method for setting the values in the list.
     * @param event Event is triggered by the above this::setStatus.
     */
    public void setStatus(ActionEvent event){
        selectedStatus = statusList.getValue();
    }
}
