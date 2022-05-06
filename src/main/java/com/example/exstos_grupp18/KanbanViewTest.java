package com.example.exstos_grupp18;

import Model.Backlog;
import Model.Task;
import Model.TaskManager;
import Model.User;
import ServerSide.Server;
import client.Client;
import controller.KanbanViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class KanbanViewTest extends Application {
    private static Main main = new Main();
    private static Stage stg;
    public static Main getInstance(){
        return main;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        stage.setResizable(true);
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(KanbanViewTest.class.getResource("KanbanView.fxml")));
        stage.setTitle("Hello!");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();
    }

    public void changeScene(String fxml) {
        Parent pane = null;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));

            pane.prefWidth(500);
            pane.prefHeight(600);

        } catch (IOException e) {
            e.printStackTrace();
        }
        stg.setResizable(false);
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
        TaskManager taskManager = new TaskManager();
        Task task1 = taskManager.createNewTask("Test1", "Coolers pistolers", "1", new User(), null, 1);
        task1.setCurrentStatus(new Backlog());
        Task task2 = taskManager.createNewTask("Test2", "Coolers pistolers", "1", new User(), null, 2);
        task2.setCurrentStatus(new Backlog());
        Task task3 = taskManager.createNewTask("Test3", "Coolers pistolers", "1", new User(), null, 3);

        ArrayList<Task> tasklist = new ArrayList<>();
        tasklist.add(task1);
        tasklist.add(task2);
        tasklist.add(task3);

        KanbanViewController controller = new KanbanViewController();
        controller.sortTasks(tasklist);
        controller.displayTasks();


    }
}