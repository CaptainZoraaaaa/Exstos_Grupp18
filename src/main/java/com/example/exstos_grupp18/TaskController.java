package com.example.exstos_grupp18;

import Model.Task;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Max Tiderman
 */
public class TaskController extends Thread implements Initializable {
    @FXML
    private Button printButton = new Button();
    @FXML
    private Label textField;
    @FXML
    private ImageView helpImage;
    @FXML
    private ChoiceBox<String> status;

    private String[] statusLog = {"Backlog", "Inprogress", "Review"};
    private String test; //Todo teststräng
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int taskId;
    private Controller controller = Controller.getInstance();

    @FXML
    void print(ActionEvent event) throws IOException { //todo javadoca
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditTask.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        ArrayList<Task> current = controller.getTask();
        for (int i = 0; i < current.size(); i++) {
            if (printButton.getParent().getId().equals(String.valueOf(current.get(i).getTASK_ID()))) {
                EditTaskController editTaskController = fxmlLoader.getController();
                editTaskController.loadedTask(current.get(i));
            }
        }



        scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void run() {
        ArrayList<Task> current = controller.getTask();
        for (int i = 0; i < current.size(); i++) {
            if (printButton.getParent().getId().equals(String.valueOf(current.get(i).getTASK_ID()))) {
                textField.setText(current.get(i).getHeader());
                printButton.setText("View task");
                javafx.scene.image.Image image = new Image("@../../../images/clock.png");
                helpImage.setImage(image);
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
