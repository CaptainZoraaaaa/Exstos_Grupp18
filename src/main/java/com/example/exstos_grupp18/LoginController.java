package com.example.exstos_grupp18;

import client.Client;
import com.example.exstos_grupp18.Main;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private Button loginBtn;
    @FXML
    private Button newUserBtn;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;
    private Controller controller = Controller.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void newUserScene (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserScene.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void logIn(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if(controller.logIn(username, password)) {
            System.out.println(">> Login successful <<");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            root = fxmlLoader.load();
            //Används för att överföra data
            MenuContrroller menuContrroller = fxmlLoader.getController();
            menuContrroller.setUserLabel(username);
            new Client(null,null,8080);

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }
        else {
            System.out.println(">> Login failed <<");
        }
    }
}