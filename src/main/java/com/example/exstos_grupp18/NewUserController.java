package com.example.exstos_grupp18;

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

/**
 * @author Max Tiderman
 */
public class NewUserController {

    @FXML
    private TextField passwordField; //TODO ändra till passwordfield
    @FXML
    private PasswordField reEnterPasswordField;
    @FXML
    private TextField usernameField;

    private Controller controller = Controller.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void newRegistration(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String reEnteredPassword = reEnterPasswordField.getText();

        if (password.equals(reEnteredPassword)) {
            if (controller.registerNewUser(username, password, null)) {
                System.out.println(">> Registration successful <<");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
                root = fxmlLoader.load();
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
            }
            else {
                System.out.println(">> Registration failed <<"); //TODO implementera felmeddelanden
            }
        }
        else {
            System.out.println(">> Passwords do not match <<"); //TODO implementera felmeddelanden
        }
    }
}
