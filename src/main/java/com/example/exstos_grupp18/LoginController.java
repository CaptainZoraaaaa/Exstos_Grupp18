package com.example.exstos_grupp18;

import client.Client;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
//TODO ändra guit
/**
 * @author Max Tiderman
 */
public class LoginController {

    private Controller controller = Controller.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;

    //todo javadoca
    public void newUserScene (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewUserView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
    //todo javadoca
    public void logIn(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(controller.logIn(username, password)) {
            System.out.println(">> Login successful <<");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            root = fxmlLoader.load();
            //Används för att överföra data
            MainMenuController mainMenuController = fxmlLoader.getController();
            mainMenuController.setUserLabel(username);

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //todo gör egen metod
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setScene(scene);

        }
        else {
            System.out.println(">> Login failed <<"); //todo skriv felmeddelande
        }
    }
}