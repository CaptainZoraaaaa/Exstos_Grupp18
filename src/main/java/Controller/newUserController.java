package Controller;

import com.example.exstos_grupp18.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class newUserController {

    @FXML
    private TextField passwordField;

    @FXML
    private TextField reEnterPasswordField;

    @FXML
    private Button signUp;

    @FXML
    private TextField usernameField;

    private Controller controller = new Controller();

    @FXML
    public void newRegistration() {
        String username = usernameField.getText();
        String password1 = passwordField.getText();
        String password2 = reEnterPasswordField.getText();

        if (password1.equals(password2)) {
            if (controller.registerNewUser(username, password1, null)) {
                System.out.println(">> Registration successful <<");
                Main main = new Main();
                main.changeScene("hello-view.fxml");
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
