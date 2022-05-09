package com.example.exstos_grupp18;
import com.example.exstos_grupp18.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuContrroller {
    @FXML
    private Button LogoutBtn;
    @FXML
    private Button canBanBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private Button projectBtn;
    @FXML
    private Label userLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private static MenuContrroller menuContrroller = new MenuContrroller();

    public static MenuContrroller getInstance(){
        return menuContrroller;
    }

    @FXML
    void changeToCanban(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }
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
    void Logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
    }
    public void setUserLabel(String name){
        userLabel.setText(name);
    }
}
