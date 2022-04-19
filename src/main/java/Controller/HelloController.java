package Controller;

import com.example.exstos_grupp18.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Button loginBtn;
    @FXML
    private Button newUserBtn;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField usernameTextField;
    private Controller controller = new Controller();

    public void newUserScene () {
        Main main = new Main();
        main.changeScene("UserScene.fxml");
    }

    public void logIn() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if(controller.logIn(username, password)) {
            System.out.println(">> Login successful <<");
        }
        else {
            System.out.println(">> Login failed <<");
        }
    }
}