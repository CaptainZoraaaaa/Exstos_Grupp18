package com.example.exstos_grupp18;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class TestCreateProject extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage =  primaryStage;
        stage.setResizable(true);
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(TestCreateProject.class.getResource("NewProject.fxml")));
        stage.setTitle("Project creation");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
