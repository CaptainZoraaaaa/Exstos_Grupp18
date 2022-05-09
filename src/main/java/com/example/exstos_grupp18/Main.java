package com.example.exstos_grupp18;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
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

    public void changeScene(String fxml) { //todo ändra till andra sättet
        Parent pane = null;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));

                pane.prefWidth(500);
                pane.prefHeight(600);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        stg.setResizable(false);
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}