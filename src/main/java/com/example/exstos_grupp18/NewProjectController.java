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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

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
    private Popup popup;

    /**
     * Method to return to previous screen.
     * @param event ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) throws IOException {
        if (popup != null) {
            if(popup.isShowing()) {
                popup.hide();
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
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
    void createNewProject(ActionEvent event) throws IOException {
        if (popup != null) {
            if(popup.isShowing()) {
                popup.hide();
            }
        }
        System.out.println("create project");
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String creator = controller.getLoggedInUser();
        if (header.length() > 5 && header.length() < 50 && deadline != null ) {
            controller.createNewProject(header, description, deadline, currentUser, creator);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            root = fxmlLoader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setScene(scene);
        }
        else {
            System.out.println(">> error message <<");
            Label label = new Label("Failed to create project: Information missing. Enter header and deadline");
            label.setTextFill(Paint.valueOf("Red"));
            popup = new Popup();
            popup.getContent().add(label);
            Stage stage2 = (Stage) creatorField.getScene().getWindow();
            popup.show(stage2);


        }
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
        creatorField.setText(controller.getLoggedInUser());
    }

    /**
     * This method is used to set a user from the ChoiceBox.
     * @param event event
     */
    public void setUsers(ActionEvent event){
        currentUser = assigneeList.getValue();
    } //TODO Se om det går att ändra till multiple choice.

    /**
     *
     * @param usernameLabel
     */
    public void setCreator(String usernameLabel) {
        creatorField.setText(usernameLabel);
    }
}

