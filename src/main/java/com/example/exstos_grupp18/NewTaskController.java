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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
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
    private TextField deadlineInputField; //todo kan vara datepicker
    @FXML
    private TextArea taskDescriptionInputField;
    @FXML
    private TextField taskHeaderInputField;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private LocalDate deadline;
    private Controller controller = Controller.getInstance();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String currentUser;
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
        Task task = new Task.TaskBuilder().header(header).description(description).estimatedTime(deadline).currentStatus(Swimlane.InProgress).id(controller.getTaskSize()).build();
        controller.createTask(task);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml")); //todo flytta till egen metod
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }
    @FXML
    void flagForHelp(ActionEvent event) {
        flagged = helpBox.isSelected();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       assigneeList.getItems().addAll(users);
       assigneeList.setOnAction(this::setUsers);
    }
    public void setUsers(ActionEvent event){
        currentUser = assigneeList.getValue();
    }
}

