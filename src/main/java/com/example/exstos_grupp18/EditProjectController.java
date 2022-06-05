package com.example.exstos_grupp18;

import Model.Project;
import Sandbox.TestController;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller for EditProject.fxml
 * @author Christian Edvall & Anna Håkansson
 */
public class EditProjectController implements Initializable {

    @FXML
    private TextField creatorField;
    @FXML
    private Button editProjectButton;
    @FXML
    private Button editButton;
    @FXML
    private DatePicker projectDeadlineDate;
    @FXML
    private TextArea projectDescriptionInputField;
    @FXML
    private TextField projectHeaderInputField;
    @FXML
    private Button addButton;
    @FXML
    private HBox assigneeBox;
    @FXML
    private TextField assigneeField;

    private Controller controller = Controller.getInstance();
    private ArrayList<String> assignees = new ArrayList<>();
    private String user;
    private LocalDate deadline;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Method to return to previous screen.
     * @param actionEvent ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "KanbanView.fxml");
    }
    /**
     * Method for setting the date to the date selected in the DatePicker.
     * @param event ActionEvent that reacts when a date chosen pressed.
     */
    @FXML
    void chooseDate(ActionEvent event) {
        deadline = projectDeadlineDate.getValue();
    }
    /**
     * This method is used to edit an existing project.
     * @param event ActionEvent that reacts when the "create" button is pressed.
     */
    /**
     * This method is used to edit a projects values.
     * @param actionEvent event
     * @author Linnéa Flystam och Christian Edvall
     */
    @FXML
    void editProject(ActionEvent actionEvent) throws IOException {
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String creator = creatorField.getText();
        LocalDate deadline1 = projectDeadlineDate.getValue();
        if (header.length() > 5 && header.length() < 50) {
            controller.editProject(header, description, deadline1, assignees, creator);
            changeScene(actionEvent, "KanbanView.fxml");
        }
        else {
            System.out.println(">> error message <<");
            Label label = new Label("Failed to edit project: Information missing. Enter header and deadline");
            label.setTextFill(Paint.valueOf("Red"));
            Popup popup = new Popup();
            popup.getContent().add(label);
            Stage stage2 = (Stage) creatorField.getScene().getWindow();
            popup.show(stage2);
        }
    }
    /**
     * Method for initializing the list of available users to select.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Project project = controller.getActiveProject();
        projectHeaderInputField.setText(project.getProjectName());
        projectDescriptionInputField.setText(project.getDescription());
        projectDeadlineDate.setValue(project.getDeadline());

        HashMap<String, Boolean> assigneeMap = controller.getActiveProject().getAssignedUser();
        for(Map.Entry<String, Boolean> assignee : assigneeMap.entrySet()) {
            if(!assignee.getValue()) {
                assignees.add(assignee.getKey());
            }
        }
        for(String user : assignees) {
            Label label = new Label(user);
            assigneeBox.getChildren().add(label);

        }

        creatorField.setText(controller.getLoggedInUser());
        projectHeaderInputField.setDisable(true);
        projectDescriptionInputField.setDisable(true);
        projectDeadlineDate.setDisable(true);
        assigneeField.setDisable(true);
        addButton.setDisable(true);
        if(!project.getAssignedUsers().get(controller.getLoggedInUser())) {
            editProjectButton.setVisible(false);
            editProjectButton.setDisable(true);
            editButton.setVisible(false);
            editButton.setDisable(true);
            addButton.setVisible(false);
            addButton.setDisable(true);
        }
        else {
            editProjectButton.setVisible(true);
            editProjectButton.setDisable(false);
            editButton.setVisible(true);
            editButton.setDisable(false);
        }
    }

    /**
     * Changes the fields to either editable or not editable
     * @param event - button click
     */
    @FXML
    void unlockFields(ActionEvent event) {
        projectHeaderInputField.setDisable(!projectHeaderInputField.isDisable());
        projectDescriptionInputField.setDisable(!projectDescriptionInputField.isDisable());
        projectDeadlineDate.setDisable(!projectDeadlineDate.isDisabled());
        addButton.setDisable(!addButton.isDisable());
        assigneeField.setDisable(!assigneeField.isDisable());
    }

    /**
     * This method is used to change a scene.
     * @param event a actionevent.
     * @param newScene a String containing the url for a scene.
     * @throws IOException throws IOException
     */
    public void changeScene(Event event, String newScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newScene));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * @author Anna Håkansson
     * takes string input from textfields and adds it
     * to assignee arraylist
     * @param event
     */
    @FXML
    public void addUserToAssignees(ActionEvent event) {
        String assignee = assigneeField.getText();
        assignees.add(assignee);
        assigneeField.clear();
        Label label = new Label(assignee);
        assigneeBox.getChildren().add(label);

    }
}