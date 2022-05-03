package Controller;

import Model.Project;
import Sandbox.TestController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditProjectController {

    @FXML
    private Button backToPreviousScreenButton;

    @FXML
    private TextField creatorField;

    @FXML
    private Button editProjectButton;

    @FXML
    private TextField projectAssigneesInputField;

    @FXML
    private DatePicker projectDeadlineDate;

    @FXML
    private TextArea projectDescriptionInputField;

    @FXML
    private TextField projectHeaderInputField;

    private Project project;
    private TestController testController = new TestController();
    private LocalDate deadline;

    /*public void initialize(){
        projectHeaderInputField.setText(project.getProjectName());
        projectDescriptionInputField.setText(project.getDescription());
        projectDeadlineDate.setValue(project.getDeadline());
    }*/
    /**
     * Method to return to previous screen.
     * @param event ActionEvent that reacts when the "back" button is pressed.
     */
    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }
    /**
     * Method for setting the date to the date selected in the DatePicker.
     * @param event ActionEvent that reacts when a date chosen pressed.
     */
    @FXML
    void chosenDate(ActionEvent event) {
        deadline = projectDeadlineDate.getValue();
    }
    /**
     * This method is used to edit an existing project.
     * @param event ActionEvent that reacts when the "create" button is pressed.
     */
    @FXML
    void editProject(ActionEvent event) {
        String header = projectHeaderInputField.getText();
        String description = projectDescriptionInputField.getText();
        String assignees = projectAssigneesInputField.getText();
        String creator = creatorField.getText();
        testController.createNewProject(header, description, deadline, creator);
    }

}