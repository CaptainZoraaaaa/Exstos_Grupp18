package com.example.exstos_grupp18;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private Button canBanBtn;
    @FXML
    private Button projectButton;
    @FXML
    private Label projectName;
    @FXML
    private Button projectNameButton;
    @FXML
    private Label userLabel;
    @FXML
    private VBox myProjectVbox;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean projectSelectionVisible;

    @FXML
    void changeProject(ActionEvent event) {
        projectName.setText(projectButton.getText());
    }

    @FXML
    void changeToCreateProject(ActionEvent event) throws IOException {
        changeScene(event, "NewProject.fxml");
    }

    @FXML
    void changeToKanban(ActionEvent event) throws IOException {
        changeScene(event, "KanbanView.fxml");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        changeScene(event,"LoginView.fxml");
    }

    @FXML
    void showProjectSelection(ActionEvent event) {
        myProjectVbox.setVisible(true);

    }
    public void changeScene (Event event, String newScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newScene));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
