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
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class is used to display the GUI for creating a new task.
 * @author Christian Edvall
 * todo: implementera back to previous screen och kommentera
 */
public class NewTaskController implements Initializable {

    @FXML
    private ChoiceBox<String> assigneeList;
    @FXML
    private TextField creatorField;
    @FXML
    private CheckBox helpBox;
    @FXML
    private TextArea taskCommentInputField;
    @FXML
    private DatePicker taskDeadlineDate;
    @FXML
    private TextArea taskDescriptionInputField;
    @FXML
    private TextField taskHeaderInputField;
    @FXML
    private ChoiceBox<Swimlane> statusList;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private LocalDate deadline;
    private Controller controller = Controller.getInstance();
    private ArrayList<String> userList = new ArrayList<>();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String currentUser;
    private boolean flagged;
    private Swimlane[] status = {Swimlane.Backlog, Swimlane.InProgress, Swimlane.Waiting, Swimlane.Done};
    private Swimlane selectedStatus;
    private KanbanViewController kanbanViewController;
    private PopOver popOver = new PopOver();
    private Popup popup = new Popup();

    /**
     * This method is used for returning to the previous screen, in this case that is the KanbanView.fxml.
     * @param event event
     * @throws IOException throws this exception.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) throws IOException {
        kanbanViewController.hideNewTaskPopOver();
    }

    /**
     * This method is used to create a new task, when the button create task is pressed this method will send the
     * entered values to the controller and change the scene to KanbanView.fxml.
     * @param event event
     * @throws IOException thrown exception.
     * @author Linnéa Flystam och Christian Edvall
     */
    @FXML
    void createNewTask(ActionEvent event) throws IOException {
        if (popup != null){
            popup.hide();
        }
        String comment = creatorField.getText() + ":\n" + taskCommentInputField.getText();
        Task task = new Task.TaskBuilder()
                .header(taskHeaderInputField.getText())
                .description(taskDescriptionInputField.getText())
                .deadline(deadline)
                .currentStatus(selectedStatus)
                .assignee(currentUser)
                .currentStatus(selectedStatus)
                .creator(creatorField.getText())
                .comments(comment)
                .flaggedForHelp(flagged)
                .id(controller.getTaskSize())
                .build();
        String header = taskHeaderInputField.getText();
        String description = taskDescriptionInputField.getText();

        if(header.length() > 1 && header.length() <= 50 && deadline != null && assigneeList != null) {
            controller.createTask(task);
            kanbanViewController.hideNewTaskPopOver();
        }
        else if(header.length() < 1) {
            System.out.println(">> error message <<");
            Label label = new Label("Failed to create task: header is missing.");
            label.setTextFill(Paint.valueOf("Red"));
            popup = new Popup();
            popup.getContent().add(label);
            popup.show(popOver);
        }
        else if(header.length() > 50){
            System.out.println(">> error message <<");
            Label label = new Label("Failed to create task: header is larger than 50.");
            label.setTextFill(Paint.valueOf("Red"));
            popup = new Popup();
            popup.getContent().add(label);
            popup.show(popOver);
        }
        else if(deadline == null){
            System.out.println(">> error message <<");
            Label label = new Label("Failed to create task: no specified deadline.");
            label.setTextFill(Paint.valueOf("Red"));
            popup = new Popup();
            popup.getContent().add(label);
            popup.show(popOver);
        }
        else if(assigneeList == null){
            System.out.println(">> error message <<");
            Label label = new Label("Failed to create task: no assignees added.");
            label.setTextFill(Paint.valueOf("Red"));
            popup = new Popup();
            popup.getContent().add(label);
            popup.show(popOver);
        }
    }

    /**
     * this method is used to set the boolean flag to false or true depending if the box is checked or not.
     * Checked box = true, unchecked box = false.
     * @param event event
     */
    @FXML
    void flagForHelp(ActionEvent event) {
        flagged = helpBox.isSelected();
    }

    /**
     * This method is used to set LocalDate deadline to the chosen date by DatePicker taskDeadlineDate using DatePicker
     * getValue.
     * @param event event
     */
    @FXML
    void chooseDate(ActionEvent event){
        deadline = taskDeadlineDate.getValue();
    }

    /**
     * This method is used to initialize the ChoiceBoxes assigneeList and statusList.
     * @param url url.
     * @param resourceBundle resourceBundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       assigneeList.getItems().addAll(users); ///This is used to att all indexes from an array to the ChoiceBox /// TODO: 2022-05-20 Denna ska istället hämta från project-medlemmar.
       assigneeList.setOnAction(this::setUsers); // this is used to select a user from the Choice
       statusList.getItems().addAll(status); //This is used to att all indexes from an array to the ChoiceBox
       statusList.setOnAction(this::setStatus); // this is used to select a user from the Choice
    }

    /**
     * This metod is used to set a status from the statusList to the variable selectedStatus.
     * @param event event
     */
    public void setStatus(ActionEvent event){
        selectedStatus = statusList.getValue();
    }

    /**
     * This method is used to set a status from the assigneeList to the variable currentUser.
     * @param event event
     */
    public void setUsers(ActionEvent event){
        currentUser = assigneeList.getValue();
    }

    /**
     * this method sets the different users to choose from in the project.
     * @param usernameLabel a username.
     */
    public void setCreator(String usernameLabel) {
        creatorField.setText(usernameLabel);
    }

    /**
     * This is a wip, the idea is to use this method to set new users to a projects list, instead of the static ones.
     * @param userList an ArrayList of users.
     */
    public void setUserList(ArrayList userList){
        this.userList = userList;
    }

    /**
     * This method is used for setting the KanbanviewController.
     * @param kanbanViewController object of KanbanviewController.
     * @author Christian Edvall
     */
    public void setKanbanViewController(KanbanViewController kanbanViewController){
        this.kanbanViewController = kanbanViewController;
    }
    /**
     * This method is used by KanbanViewController to send the popover to NewTaskController so that NewTaskController
     * can close the popover when done.
     * @param popOver a popover.
     */
    public void setPopOver(PopOver popOver){
        this.popOver = popOver;
    }
}

