package com.example.exstos_grupp18;

import Model.Package;
import Model.ServerStub;
import Model.User;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

/**
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
    private ServerStub serverStub;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    @FXML
    public void newRegistration(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String reEnteredPassword = reEnterPasswordField.getText();

        if (password.equals(reEnteredPassword)) {
            if (controller.registerNewUser(username, password, null)) {
                connect("localhost", 8080);
                System.out.println("fick Strömmar");
                User user = new User.UserBuilder().username(username).password(password).build();
                Package newUser = new Package.PackageBuilder().sender(user).type(Package.NEW_USER_REGISTRATION).build();
                try {
                    oos.writeObject(newUser);
                    oos.flush();
                    Package p = (Package) ois.readObject();
                }catch (IOException | ClassNotFoundException e){
                    e.printStackTrace();
                }
                /*System.out.println(">> Registration successful <<");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
                root = fxmlLoader.load();
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);*/
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
    private void connect(String ip, int port) throws IOException {
        this.socket = new Socket(ip,port);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }
}
