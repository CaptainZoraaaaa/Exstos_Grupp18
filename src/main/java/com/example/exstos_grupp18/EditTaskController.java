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
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controller for EditTask.fxml.
 * @author Christian Edvall
 */
public class EditTaskController implements Initializable {

    @FXML
    private ChoiceBox<Swimlane> statusList;
    @FXML
    private ChoiceBox<String> assigneeList; //todo kan detta göras så att man kan välja flera alternativ.
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
    private Swimlane[] status = {Swimlane.Backlog, Swimlane.InProgress, Swimlane.Waiting, Swimlane.Done};
    private Swimlane selectedStatus;
    private String selectedUser;
    private boolean flagged;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Task currentTask;
    private Controller controller = Controller.getInstance();

    /**
     * Method for returning to previous screen.
     * @param event triggered by clicking the button backToPreviousScreenButton.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }

    /**
     * Method for saving changes in a task, at the moment this is connected to the TestController.
     * @param event ActionEvent.
     */
    @FXML
    void saveChanges(ActionEvent event) throws IOException {
        String comment = String.format("%s: %n %s%n", creatorField.getText(), taskCommentInputField.getText());
        System.out.println(comment);

        currentTask.setHeader(taskHeaderInputField.getText());
        currentTask.setDescription(taskDescriptionInputField.getText());
        currentTask.setDeadline(deadline);
        currentTask.setAssignees(selectedUser); // TODO: 2022-05-12 Denna funkar inte som den ska.
        currentTask.setCurrentStatus(selectedStatus);
        currentTask.setCreator(creatorField.getText());
        currentTask.setComments(comment);
        currentTask.setFlaggedForHelp(flagged);
        controller.taskEdited(currentTask);


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }

    /**
     * Method for setting the boolean value flagged to true och done, determined if the box is checked or not. If it's
     * checked boolean will be set to true, otherwise false.
     *
     * @param event triggered by clicking on the checkbox for flag for help.
     */
    @FXML
    void flagForHelp(ActionEvent event) {
        if (helpBox.isSelected()) {
            flagged = true;
        } else {
            flagged = false;
        }
    }
    //// TODO: 2022-06-03 javadoc. 
    @FXML
    void setTaskToEditable(ActionEvent event) {
        taskHeaderInputField.setEditable(true);
        taskDescriptionInputField.setEditable(true);
        taskDeadlineDate.setDisable(false);
        assigneeList.setDisable(false);
        statusList.setDisable(false);
        taskCommentInputField.setEditable(true);
        helpBox.setDisable(false);
    }
    //// TODO: 2022-06-03 javadoc 
    @FXML
    void chooseDate(ActionEvent event) {
        deadline = taskDeadlineDate.getValue();
    }

    /**
     * Method for initializing the task ChoiceBoxes with users to select as assignees and status as statuses.
     *
     * @param url            url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //todo lägg till kommentarer i metodkropp
        assigneeList.getItems().addAll(users);
        assigneeList.setOnAction(this::setUsers);
        statusList.getItems().addAll(status);
        statusList.setOnAction(this::setStatus);
        creatorField.setText(controller.getLoggedInUser());
    }

    /**
     * Method for setting the values in the list.
     * @param event Event is triggered by the above this::setUsers.
     */
    public void setUsers(ActionEvent event) {
        selectedUser = assigneeList.getValue();
    }

    /**
     * Method for setting the values in the list.
     * @param event Event is triggered by the above this::setStatus.
     */
    public void setStatus(ActionEvent event) {
        selectedStatus = statusList.getValue();
    }
    //// TODO: 2022-06-03 Javadoc
    public void loadedTask(Task task) {
        this.currentTask = task;
        taskHeaderInputField.setText(task.getHeader());
        taskDescriptionInputField.setText(task.getDescription());
        taskDeadlineDate.setValue(task.getDeadline());
        assigneeList.setValue(task.getAssignees().get(0));
        statusList.setValue(task.getCurrentStatus());
        creatorField.setText(task.getCreator());
        StringBuilder comments = new StringBuilder();
        for(String comment : task.getComments()) {
            comments.append(comment);
        }
        String finalString = comments.toString();
        System.out.println(finalString);
        taskCommentInputField.setText(finalString);
        if (task.isFlaggedForHelp()) {
            helpBox.setSelected(true);
        }
    }
}
