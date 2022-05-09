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
import javafx.stage.Stage;
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
    private TextArea textField;
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
    void print(ActionEvent event) { //todo javadoca
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
            if (printButton.getParent().getId().equals(String.valueOf(current.get(i).getTASK_ID()))) {
                    textField.setText(current.get(i).getDescription());
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
