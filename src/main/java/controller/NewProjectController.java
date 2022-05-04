package controller;

import Sandbox.TestController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class NewProjectController {

    @FXML
    private Button backToPreviousScreenButton;

    @FXML
    private Button createNewProjectButton;

    @FXML
    private TextField creatorField;

    @FXML
    private TextField projectAssigneesInputField;

    @FXML
    private DatePicker projectDeadlineDate;

    @FXML
    private TextArea projectDescriptionInputField;

    @FXML
    private TextField projectHeaderInputField;

    private LocalDate deadline;

    private TestController testController = new TestController();

    /**
     * Method to return to previous screen.
     * @param event ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }

    /**
     * This method is used to create a new project.
     * @param event ActionEvent that reacts when the "create" button is pressed.
     */
    @FXML
    void createNewProject(ActionEvent event) {
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String assignees = projectAssigneesInputField.getText();
        String creator = creatorField.getText();
        testController.createNewProject(header, description, deadline, creator);
    }

    /**
     * Method for setting the date to the date selected in the DatePicker.
     * @param event ActionEvent that reacts when a date chosen pressed.
     */
    @FXML
    void chosenDate(ActionEvent event) {
        deadline = projectDeadlineDate.getValue();
    }
}

