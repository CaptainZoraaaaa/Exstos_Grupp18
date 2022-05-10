package com.example.exstos_grupp18;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    @FXML
    public void newRegistration() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String reEnteredPassword = reEnterPasswordField.getText();

        if (password.equals(reEnteredPassword)) {
            if (controller.registerNewUser(username, password, null)) {
                System.out.println(">> Registration successful <<");
                Main main = new Main();
                main.changeScene("LoginView.fxml");
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
