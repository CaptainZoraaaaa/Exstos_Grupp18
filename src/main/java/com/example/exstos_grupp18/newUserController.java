package com.example.exstos_grupp18;

import com.example.exstos_grupp18.Main;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class newUserController {

    @FXML
    private TextField passwordField;

    @FXML
    private PasswordField reEnterPasswordField;

    @FXML
    private Button signUp;

    @FXML
    private TextField usernameField;

    private Controller controller = Controller.getInstance();

    @FXML
    public void newRegistration() {
        String username = usernameField.getText();
        String password1 = passwordField.getText();
        String password2 = reEnterPasswordField.getText();

        if (password1.equals(password2)) {
            if (controller.registerNewUser(username, password1, null)) {
                System.out.println(">> Registration successful <<");
                Main main = new Main();
                main.changeScene("LoginView.fxml");
            }
            else {
                System.out.println(">> Registration failed <<");
            }
        }
        else {
            System.out.println(">> Passwords do not match <<");
        }

    }

}
