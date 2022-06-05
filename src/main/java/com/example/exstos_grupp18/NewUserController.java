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

import java.io.*;

/**
 * this class is used as a Controller for the NewUser.fxml.
 * @author Max Tiderman
 */
public class NewUserController {

    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField reEnterPasswordField;
    @FXML
    private TextField usernameField;

    private Controller controller = Controller.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * this methos is used to create a new user.
     * @param event triggered by clicking on register.
     * @throws IOException throw an IOException.
     */
    @FXML
    public void newRegistration(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String reEnteredPassword = reEnterPasswordField.getText();

        if (password.equals(reEnteredPassword)) {
            if (controller.registerOnServer(username, password)) {
                System.out.println(">> Registration successful <<");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
                root = fxmlLoader.load();
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                //// TODO: 2022-06-03 popup för lyckad försök. 
            }
            else {
                System.out.println(">> Registration failed <<"); //TODO implementera felmeddelanden
            }
        }
        else {
            System.out.println(">> Passwords do not match <<"); //TODO implementera felmeddelanden
        }
    }

    /**
     * This method is used to return to the previous screen in this case LogInView.fxml.
     * @author Christian Edvall
     * @param event event
     * @throws IOException throws this exception.
     */
    @FXML
    void backToPreviousScreenButton(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
