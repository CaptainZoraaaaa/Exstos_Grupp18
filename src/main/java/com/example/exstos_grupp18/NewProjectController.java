package com.example.exstos_grupp18;


import Sandbox.TestController;
import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
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
    @FXML
    private Button createNewProjectButton;

    private LocalDate deadline;
    private Controller controller = Controller.getInstance();
    private TestController testController = new TestController();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"}; //todo temporärt
    private String currentUser;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private HomePageController homePageController;
    private PopOver popOver;

    /**
     * Method to return to previous screen.
     * @param event ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) throws IOException {
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
     * @author Linnéa Flystam och Christian Edvall
     */
    @FXML
    void createNewProject(ActionEvent event) throws IOException {
        System.out.println("create project");
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String creator = controller.getLoggedInUser();
        if (header.length() > 5 && header.length() < 50 && deadline != null ) {
            controller.createNewProject(header, description, deadline, currentUser, creator);
            homePageController.hideProjectPopOver();
        }
        else {
            System.out.println(">> error message <<");
            Label label = new Label("Failed to create project: Information missing. Enter header and deadline");
            label.setTextFill(Paint.valueOf("Red"));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(popOver);



        }
    }
    /**
     * Method for setting the date to the date selected in the DatePicker.
     * @param event ActionEvent that reacts when a date chosen pressed.
     */
    @FXML
    void chooseDate(ActionEvent event) {
        deadline = projectDeadlineDate.getValue();
    }

    public void setHomePageController(HomePageController homePageController) {
        this.homePageController = homePageController;
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
     *This method sets the creator field to who is currently logged in.
     * @param usernameLabel a String containing the logged in person.
     */
    public void setCreator(String usernameLabel) {
        creatorField.setText(usernameLabel);
    }

    /**
     * This method takes in a popover as a parameter and sets it to the instance variable, allowing use of the popup in
     * the popover for later usages.
     * @author Christian Edvall
     * @param popOver Popover object.
     */
    public void sendPopOver(PopOver popOver) {
        this.popOver = popOver;
    }
}

