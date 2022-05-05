package com.example.exstos_grupp18;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskController implements Initializable {
    private KanbanViewController kanbanViewController;
    @FXML
    private Button printBtn;
    @FXML
    private TextArea textField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void print(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KanbanView.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.kanbanViewController = fxmlLoader.getController();
        kanbanViewController.skriv(printBtn.getParent());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
