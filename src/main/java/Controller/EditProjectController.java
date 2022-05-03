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

    /*public void initialize(){
        projectHeaderInputField.setText(project.getProjectName());
        projectDescriptionInputField.setText(project.getDescription());
        projectDeadlineDate.setValue(project.getDeadline());
    }*/

    @FXML
    void backToPreviousScreen(ActionEvent event) {

    }

    @FXML
    void chosenDate(ActionEvent event) {

    }

    @FXML
    void createNewProject(ActionEvent event) {

    }

}