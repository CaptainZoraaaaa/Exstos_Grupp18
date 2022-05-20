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
import javafx.stage.Stage;

import java.io.IOException;
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
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Method to return to previous screen.
     * @param actionEvent ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "HomePage.fxml");
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
     */
    @FXML
    void editProject(ActionEvent actionEvent) throws IOException {
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String creator = creatorField.getText();
        controller.createNewProject(header, description, deadline, user, creator);
        changeScene(actionEvent, "HomePage.fxml");
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
    public void changeScene(Event event, String newScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newScene));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}