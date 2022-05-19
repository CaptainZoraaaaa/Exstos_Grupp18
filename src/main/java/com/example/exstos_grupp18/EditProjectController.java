package com.example.exstos_grupp18;

import Model.Project;
import Sandbox.TestController;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Christian Edvall
 */
public class EditProjectController implements Initializable {

    @FXML
    private Button backToPreviousScreenButton;
    @FXML
    private TextField creatorField;
    @FXML
    private Button editProjectButton;
    @FXML
    private ChoiceBox<String> assigneeList;
    @FXML
    private DatePicker projectDeadlineDate;
    @FXML
    private TextArea projectDescriptionInputField;
    @FXML
    private TextField projectHeaderInputField;
    private Project project;
    private Controller controller = Controller.getInstance();
    private LocalDate deadline;
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String user;

    /**
     * Method to return to previous screen.
     * @param event ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) { //todo behöver kunna byta till föregående scen (antagligen kanban eller huvudvy)
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
     * @param event event
     */
    @FXML
    void editProject(ActionEvent event) {
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String creator = creatorField.getText();
        controller.createNewProject(header, description, deadline, user, creator);
    }
    /**
     * Method for initializing the list of available users to select.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assigneeList.getItems().addAll(users); //This is used to att all indexes from an array to the ChoiceBox
        assigneeList.setOnAction(this::setUsers); // this is ues to select a user from the Choice
        creatorField.setText(controller.getLoggedInUser());
    }
    /**
     * Method for setting the values in the assigneeList.
     * @param event event.
     */
    private void setUsers(ActionEvent event) {
        user = assigneeList.getValue();
    } //TODO kolla om det går att ändra till multiple choise
}