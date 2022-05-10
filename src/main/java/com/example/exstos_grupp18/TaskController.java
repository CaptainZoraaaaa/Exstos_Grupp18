package com.example.exstos_grupp18;

import Model.Task;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Max Tiderman
 */
public class TaskController extends Thread implements Initializable {
    @FXML
    private Button printButton = new Button();
    @FXML
    private TextArea textField;
    @FXML
    private ChoiceBox<String> status;
    @FXML
    private ImageView flagImage;

    private String[] statusLog = {"Backlog", "Inprogress", "Review"};
    private String test; //Todo teststräng
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int taskId;
    private Controller controller = Controller.getInstance();

    @FXML
    void print(ActionEvent event) { //todo javadoca

    }

    @Override
    public void run() {
        ArrayList<Task> current = controller.getTask();
        for (int i = 0; i < current.size(); i++) {
            if (printButton.getParent().getId().equals(String.valueOf(current.get(i).getTASK_ID()))) {
                textField.setText(current.get(i).getDescription());
                if (current.get(i).isFlaggedForHelp()){
                    Image image = null;
                    try {
                        image = new Image(String.valueOf(new File("src/main/resources/images/orange_flag.png").toURI().toURL().toExternalForm()));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    flagImage.setImage(image);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    } //todo javadoca
    public void setUsers(ActionEvent event){
        test = status.getValue();
    } //todo javadoca
}
