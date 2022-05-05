package controller;

import Sandbox.TestController;
import com.example.exstos_grupp18.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private TestController testController = new TestController();
    private String[] users = {"Anna", "Christian", "Emma", "Linnéa", "Max"};
    private String user;


    /**
     * Method to return to previous screen.
     * @param event ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) {
        Main main = new Main();
        main.changeScene("Menu.fxml");
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

