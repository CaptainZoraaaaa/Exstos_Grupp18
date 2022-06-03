package Sandbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class TestHomePage extends Application {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(true);
        stage.setFullScreen(true);
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(TestEditTask.class.getResource("HomePage.fxml")));
        stage.setTitle("Homepage");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
