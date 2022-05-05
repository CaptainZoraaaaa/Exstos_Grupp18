package com.example.exstos_grupp18;

import ServerSide.Server;
import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static Main main = new Main();
    private static Stage stg;

    public static Main getInstance(){
        return main;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        stage.setResizable(true);
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
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
        //Server server = new Server();
        launch();
    }
}