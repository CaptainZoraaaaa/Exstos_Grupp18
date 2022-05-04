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

    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }

    @FXML
    void createNewProject(ActionEvent event) {
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String assignees = projectAssigneesInputField.getText();
        String creator = creatorField.getText();
        testController.createNewProject(header, description, deadline, creator);
    }

    @FXML
    void chosenDate(ActionEvent event) {
        deadline = projectDeadlineDate.getValue();
    }
}

