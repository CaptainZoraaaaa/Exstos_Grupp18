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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TaskController extends Thread implements Initializable {
    private Controller controller = Controller.getInstance();
    @FXML
    private Button printBtn = new Button();
    @FXML
    private TextArea textField;
    @FXML
    private ChoiceBox<String> status;
    private String[] statusLog = {"Backlog", "Inprogress", "Review"};
    private String test;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int id;

    public TaskController() {

    }

    @FXML
    void print(ActionEvent event) {
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //kanbanViewController.skriv(test);
        ArrayList<Task> current = controller.getTask();



        for (int i = 0; i < current.size(); i++) {
            //System.out.println(printBtn.getParent().getId().equals(String.valueOf(current.get(i).getTASK_ID()))+" ID:: "+printBtn.getParent().getId());
            //System.out.println();
            //System.out.println(printBtn.getParent().getId() + " : " + current.get(i).getTASK_ID());
            if (printBtn.getParent().getId().equals(String.valueOf(current.get(i).getTASK_ID()))) {
                    textField.setText(current.get(i).getDescription());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    }
    public void setUsers(ActionEvent event){
        test = status.getValue();
    }
}
