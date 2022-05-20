package com.example.exstos_grupp18;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TestMain2 extends Application {
    private static Main main = new Main(); //todo ta bort när vi har ändrat
    private static Stage stg;

    public static Main getInstance(){
        return main;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginView.fxml")));
        stage.setTitle("Exsto");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}