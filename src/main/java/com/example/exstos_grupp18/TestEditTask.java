package com.example.exstos_grupp18;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class TestEditTask extends Application {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(true);
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(TestEditTask.class.getResource("EditTask.fxml")));
        stage.setTitle("Edit task");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
