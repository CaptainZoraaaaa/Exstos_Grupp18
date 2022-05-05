package com.example.exstos_grupp18;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class TestGUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("KanbanView.fxml")));
        stage.setTitle("Hello!");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
