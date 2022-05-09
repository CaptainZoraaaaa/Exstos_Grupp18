package com.example.exstos_grupp18;

import Sandbox.TestController;
import com.example.exstos_grupp18.Main;
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

public class NewProjectController implements Initializable {

    @FXML
    private Button backToPreviousScreenButton;
    @FXML
    private Button createNewProjectButton;
    @FXML
    private TextField creatorField;
    @FXML
    private ChoiceBox<String> chosenAssignees;
    @FXML
    private DatePicker projectDeadlineDate;
    @FXML
    private TextArea projectDescriptionInputField;
    @FXML
    private TextField projectHeaderInputField;
    private LocalDate deadline;
    private Controller testController = Controller.getInstance();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String user;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Method to return to previous screen.
     * @param event ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }

    /**
     * This method is used to create a new project.
     * @param event ActionEvent that reacts when the "create" button is pressed.
     */
    @FXML
    void createNewProject(ActionEvent event) {
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String creator = creatorField.getText();
        testController.createNewProject(header, description, deadline, user, creator);
    }

    /**
     * Method for setting the date to the date selected in the DatePicker.
     * @param event ActionEvent that reacts when a date chosen pressed.
     */
    @FXML
    void chosenDate(ActionEvent event) {
        deadline = projectDeadlineDate.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chosenAssignees.getItems().addAll(users);
        chosenAssignees.setOnAction(this::setUsers);
    }
    public void setUsers(ActionEvent event){
        user = chosenAssignees.getValue();
    }
}

