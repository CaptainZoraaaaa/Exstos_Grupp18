package com.example.exstos_grupp18;

import Model.Project;
import Model.Swimlane;
import Model.Task;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class is used to set up the scene Home Page.
 * @author Christian Edvall & Emma Mörk.
 * @version 2022-05-10.
 */
public class HomePageController implements Initializable {


    @FXML
    private Button canBanBtn;
    @FXML
    private Button createProjectButton;
    @FXML
    private Label projectName;
    @FXML
    private Button projectNameButton;
    @FXML
    private Label userLabel;
    @FXML
    private ScrollPane myProjectVbox;
    @FXML
    private VBox myLane;
    @FXML
    private VBox projectList;
    @FXML
    private ListView<Swimlane> myListLane;
    @FXML
    private ScrollPane myLaneScroll;
    @FXML
    private Button editProjectButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean projectSelectionVisible;
    private Controller controller = Controller.getInstance();
    private PopOver popOver = new PopOver();

    /**
     * This method is used to change the project you're working in by changing the project name.
     * @param event an actionevent when clicking on a project in the project menu for selecting projects.
     */
    @FXML
    void changeProject(ActionEvent event) {
        Button button = (Button) event.getSource();
        System.out.println(button.getText());
        projectName.setText(button.getText());
        controller.changeProject(button.getText());
    }

    /**
     * This method is used to change to the scene for creating a project.
     * @param event an actionevent when clicking on Create project in the menu.
     * @throws IOException Input/output-exception.
     */
    @FXML
    void changeToCreateProject(ActionEvent event) throws IOException {
        //changeScene(event, "NewProject.fxml");
        createProjectPopOver();
    }
    @FXML
    void createProjectPopOver(){
        if(popOver == null || !popOver.isShowing()){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewProject.fxml"));
                Node newProjectRoot =  fxmlLoader.load();
               // NewProjectController newProjectController = fxmlLoader.getController();
                popOver = new PopOver(newProjectRoot);
                popOver.setTitle(" ");
                popOver.setDetachable(false);
                popOver.setHeaderAlwaysVisible(false);
                popOver.show(createProjectButton, 330, 165  );
                //popOver.getStyleClass().add("popOver");

            } catch (IOException e ){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void hideProjectPopOver(){
        popOver.setAutoHide(true);
    }

    /**
     * This method is used to change scene to the Kanban board.
     * @param event an actionevent when clicking on View kanban in the menu.
     * @throws IOException Input/output-exception.
     */
    @FXML
    void changeToKanban(ActionEvent event) throws IOException {
        changeScene(event, "KanbanView.fxml");
    }
    /**
     * This method is used to log out of the project.
     * @param event an actionevent when clicking on log out in the menu.
     * @throws IOException Input/output-exception.
     */
    @FXML
    void logOut(ActionEvent event) throws IOException {
        changeScene(event, "LoginView.fxml");
    }

    /**
     * This method is used to display the project menu, it's hidden by default.
     * @param event an actionevent that displays the project menu och hides it when clicked.
     */
    @FXML
    void showProjectSelection(ActionEvent event) {
        if (!projectSelectionVisible) {
            myProjectVbox.setVisible(true);
            projectSelectionVisible = true;
        } else {
            myProjectVbox.setVisible(false);
            projectSelectionVisible = false;
        }
    }

    /**
     * This method is used to change the scene to edit a project.
     * @param actionEvent an actionevent that displays the scene EditProject when clicked.
     * @throws IOException Input/output-exception.
     */
    @FXML
    void editProjectPressed(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "EditProject.fxml");
    }

    /**
     * This method is a template for changing a scene, it can be called upon by any methods and ca change to whatever
     * scene is specified.
     * @param event takes an actionevent from the calling method.
     * @param newScene a String containing a scenes location.
     * @throws IOException Input/output-exception.
     */
    public void changeScene(Event event, String newScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newScene));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * This method is used to initialize the scene, by doing so the scene can come pre-loaded with user information
     * such as: who the logged in user is, what tasks they're assigned and what projects they are members of.
     * @param url is a url
     * @param resourceBundle is a resourcebundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLabel.setText(controller.getLoggedInUser());
        myProjectVbox.setVisible(false);
        ArrayList<Project> currentList = controller.getAllProject();
        Button button = null;
            for (int i = 0; i < currentList.size(); i++) {
                button = new Button(currentList.get(i).getProjectName());
                button.setId(String.valueOf(currentList.get(i).getProjectID()));
                button.getStyleClass().add("Men");
                button.setOnAction(this::changeProject);
                projectList.getChildren().addAll(button);
            }
        System.out.println(LocalDate.now());
    }

    /**
     * This method is used by other scenes to set up tasks on the HomePage swimlane.
     */
    public void setUpMySwimLane(){
        ArrayList<Task> current = controller.getTask();
        Node[] nodes = new Node[controller.getTaskSize()];

        for (int i = 0; i < controller.getTaskSize(); i++) {
            try {
                if (current.get(i).getAssignees().contains(userLabel.getText())) {
                    nodes[i] = FXMLLoader.load(getClass().getResource("Task.fxml"));
                    nodes[i].setId(String.valueOf(i));
                    myLane.getChildren().add(nodes[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
