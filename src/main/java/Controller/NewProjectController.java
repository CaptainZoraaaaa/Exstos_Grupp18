package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class NewProjectController {

    @FXML
    private Button backToPreviousScreenButton;

    @FXML
    private Button createNewProjectButton;

    @FXML
    private Label creatorLabel;

    @FXML
    private TextField projectAssigneesInputField;

    @FXML
    private DatePicker projectDeadlineDate;

    @FXML
    private TextField projectDescriptionInputField;

    @FXML
    private TextField projectHeaderInputField;

    private LocalDate deadline;

    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }

    @FXML
    void createNewProject(ActionEvent event) {
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String assignees = projectAssigneesInputField.getText();
        String creator = creatorLabel.getText();
    }

    @FXML
    void chosenDate(ActionEvent event) {
        deadline = projectDeadlineDate.getValue();
    }
}
