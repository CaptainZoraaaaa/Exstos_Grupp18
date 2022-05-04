package controller;
import com.example.exstos_grupp18.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    void changeToCanban(ActionEvent event) {

    }
    @FXML
    void changeToCreateProject(ActionEvent event) {
        new Main().changeScene("NewProject.fxml");
    }
    @FXML
    void Logout(ActionEvent event) {
        new Main().changeScene("hello-view.fxml");
    }
}
