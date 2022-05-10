package com.example.exstos_grupp18;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Max Tiderman
 */
public class MainMenuController {
    @FXML
    private Label userLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    //todo javadoca
    @FXML
    void changeToKanban(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }

    //todo javadoca
    @FXML
    void changeToCreateProject(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewProject.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }
    @FXML
    void changeToMyProfile(ActionEvent event) throws IOException {
        changeScene(event,"myPage.fxml");
    }

    //todo javadoca
    @FXML
    void logOut(ActionEvent event) throws IOException {
        changeScene(event,"LoginView.fxml");
    }

    //todo javadoca
    public void setUserLabel(String name){
        userLabel.setText(name);
    }

    public void changeScene (Event event, String newScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newScene));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
