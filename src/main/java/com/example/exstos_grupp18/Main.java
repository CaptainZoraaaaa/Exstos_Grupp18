package com.example.exstos_grupp18;

import controller.Controller;
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
    @Override
    public void stop() throws Exception {
        Controller controller = Controller.getInstance();
        controller.logOut();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}