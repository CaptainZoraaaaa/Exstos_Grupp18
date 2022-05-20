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
import javafx.stage.Stage;
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

    /**
     * This method is used for returning to the previous screen, in this case that is the KanbanView.fxml.
     * @param event event
     * @throws IOException throws this exception.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }

    /**
     * This method is used to create a new task, when the button create task is pressed this method will send the
     * entered values to the controller and change the scene to KanbanView.fxml.
     * @param event event
     * @throws IOException thrown exception.
     */
    @FXML
    void createNewTask(ActionEvent event) throws IOException {
        String comment = creatorField.getText() + ":\n" + taskCommentInputField.getText();
        Task task = new Task.TaskBuilder()
                .header(taskHeaderInputField.getText())
                .description(taskDescriptionInputField.getText())
                .estimatedTime(deadline)
                .currentStatus(selectedStatus)
                .assignee(currentUser)
                .currentStatus(selectedStatus)
                .creator(creatorField.getText())
                .comments(comment)
                .flaggedForHelp(flagged)
                .id(controller.getTaskSize())
                .build();
        controller.createTask(task);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        root = fxmlLoader.load();
        KanbanViewController kanbanViewController = fxmlLoader.getController();
        kanbanViewController.setUserLabel(creatorField.getText());
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
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

    public void setCreator(String usernameLabel) {
        creatorField.setText(usernameLabel);
    }
    public void setUserList(ArrayList userList){
        this.userList = userList;
    }
}

