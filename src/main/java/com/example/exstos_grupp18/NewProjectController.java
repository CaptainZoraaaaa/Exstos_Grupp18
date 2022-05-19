package com.example.exstos_grupp18;


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
 * This class is a GUI and displays a page for creating a new project.
 * @author Christian Edvall
 */
public class NewProjectController implements Initializable {

    @FXML
    private TextField creatorField;
    @FXML
    private ChoiceBox<String> assigneeList;
    @FXML
    private DatePicker projectDeadlineDate;
    @FXML
    private TextArea projectDescriptionInputField;
    @FXML
    private TextField projectHeaderInputField;

    private LocalDate deadline;
    private Controller controller = Controller.getInstance();
    private TestController testController = new TestController();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"}; //todo temporärt
    private String currentUser;
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
        controller.createNewProject(header, description, deadline, currentUser, creator);
        //testController.createNewProject(projectHeaderInputField.getText(), projectDescriptionInputField.getText(), deadline, currentUser, creatorField.getText());
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
     * This method is used to initialize the ChoiceBox for assignees with users from the users array.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assigneeList.getItems().addAll(users); //This is used to att all indexes from an array to the ChoiceBox
        assigneeList.setOnAction(this::setUsers); // this is used to select a user from the Choice
    }

    /**
     * This method is used to set a user from the ChoiceBox.
     * @param event event
     */
    public void setUsers(ActionEvent event){
        currentUser = assigneeList.getValue();
    } //TODO Se om det går att ändra till multiple choice.
}

