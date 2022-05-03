package com.example.exstos_grupp18;

import Model.Project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Objects;

public class TestEditProject extends Application {
    private Stage stage1;
    Project project = new Project.ProjectBuilder()
            .projectName("Exsto")
            .description("Världens bästa planeringsapp gjord av Grupp 18, den bästa gruppen av alla grupper")
            .deadline(LocalDate.now())
            .build();

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage1 = primaryStage;
        stage1.setResizable(true);
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(TestEditProject.class.getResource("EditProject.fxml")));
        stage1.setUserData(project);
        stage1.setTitle("Project edit");
        stage1.setScene(new Scene(fxmlLoader));
        stage1.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
