package controller;

import client.Client;
import com.example.exstos_grupp18.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    public void newUserScene () {
        Main main = new Main();
        main.changeScene("UserScene.fxml");
    }

    public void logIn() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if(controller.logIn(username, password)) {
            System.out.println(">> Login successful <<");
            new Main().changeScene("Menu.fxml");
            new Client(null,"localhost",8080);
        }
        else {
            System.out.println(">> Login failed <<");
        }
    }
}