package com.example.exstos_grupp18;

import Model.Swimlane;
import Model.Task;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private Button canBanBtn;
    @FXML
    private Button projectButton;
    @FXML
    private Label projectName;
    @FXML
    private Button projectNameButton;
    @FXML
    private Label userLabel;
    @FXML
    private VBox myProjectVbox;
    @FXML
    private VBox myLane;
    @FXML
    private ListView<Swimlane> myListLane;
    @FXML
    private ScrollPane myLaneScroll;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean projectSelectionVisible;
    private Controller controller = Controller.getInstance();

    @FXML
    void changeProject(ActionEvent event) {
        projectName.setText(projectButton.getText());
    }

    @FXML
    void changeToCreateProject(ActionEvent event) throws IOException {
        changeScene(event, "NewProject.fxml");
    }

    @FXML
    void changeToKanban(ActionEvent event) throws IOException {
        changeScene(event, "KanbanView.fxml");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        changeScene(event, "LoginView.fxml");
    }

    @FXML
    void showProjectSelection(ActionEvent event) {
        if (!projectSelectionVisible) {
            myProjectVbox.setVisible(true);
            projectSelectionVisible = true;
        } else {
            myProjectVbox.setVisible(false);
            projectSelectionVisible = false;
        }
    }

    public void changeScene(Event event, String newScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newScene));
        root = fxmlLoader.load();
        KanbanViewController mainMenuController = fxmlLoader.getController();
        mainMenuController.setUserLabel(userLabel.getText());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void setUserLabel(String name){
        userLabel.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myProjectVbox.setVisible(false);

        ArrayList<Task> current = controller.getTask();
        Node[] nodes = new Node[controller.getTaskSize()];

        for (int i = 0; i < controller.getTaskSize(); i++) {
            try {
                if (current.get(i).getAssignees().equals(userLabel)) {
                    nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                    Node scene = nodes[i];
                    nodes[i].setId(String.valueOf(i));
                    //System.out.println(nodes[i].getId());
                    myLane.getChildren().add(nodes[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
