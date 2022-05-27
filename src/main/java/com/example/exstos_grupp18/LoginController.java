package com.example.exstos_grupp18;

import client.Client;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.io.IOException;
//TODO ändra guit
/**
 * @author Max Tiderman & Anna Håkansson
 */
public class LoginController {

    private Controller controller = Controller.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Popup popup;

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;

    /**
     * This method runs the NewUserView which is a separate scene.
     * @param event an actionevent when clicking on New user in the menu.
     * @throws IOException In/out exception.
     */
    public void newUserScene (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewUserView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * This method handles login requests and authenticates credentials, when credentials are correct the user can
     * continue to the next scene, if not a error message will show.
     * @param event an actionevent when clicking on the button Login.
     * @throws IOException In/Out exception.
     */
    public void logIn(ActionEvent event) throws IOException {
        if(popup != null){
            popup.hide();
        }
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        // Change logInTest to logIn() for real use;
        if(controller.logIn(username, password)) {
            controller.setLoggedInUser(username);
            System.out.println(">> Login successful <<");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            root = fxmlLoader.load();
            System.out.println(username);
            //Används för att överföra data
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //todo gör egen metod
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setScene(scene);
        }
        else {
            Label label = new Label("Failed to log in: Wrong credentials. Enter matching username and password");
            label.setTextFill(Paint.valueOf("Red"));
            popup = new Popup();
            popup.getContent().add(label);
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            popup.setX(900.0);
            popup.setY(660.0);
            popup.show(stage);
            System.out.println(">> Login failed <<");
        }
    }
}