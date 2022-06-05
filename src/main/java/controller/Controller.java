package controller;

import Model.*;
import Model.DataPackage;
import client.Client;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for handling the control flow on the client side
 * of the application
 *
 * @author Max Tiderman & Anna Håkansson
 */
public class Controller {
    private User user;
    private Client client;
    private ArrayList<Project> projects = new ArrayList<>();
    private Project activeProject = null;
    private static Controller controller = new Controller();
    private Swimlane swimLaneType;

    private String loggedInUser;
    private int currentTaskId;


    public static Controller getInstance(){
        return controller;
    }

    /**
     * @author Anna Håkansson
     *
     * method for saving the edited information and send the update to the server
     * @param header
     * @param description
     * @param deadline
     * @param assignees
     * @param creator
     */
    public void editProject(String header, String description, LocalDate deadline, ArrayList<String> assignees, String creator) {
        activeProject.setProjectName(header);
        activeProject.setDescription(description);
        activeProject.setDeadline(deadline);
        for (String user: assignees) {
            if(!activeProject.getAssignedUsers().containsKey(user)) {
                activeProject.getAssignedUser().put(user, false);
            }
        }
        DataPackage dataPackage = new DataPackage.PackageBuilder()
                .project(activeProject)
                .packageType(DataPackage.PROJECT_EDITED)
                .build();
        client.sendUpdate(dataPackage);
    }

    /**
     * @author Anna Håkansson
     *
     * method for registrating a new user on the server, returns if registration was ok
     * connects with a temporary socket
     * @param username
     * @param password
     * @return if registration was ok
     */
    public boolean registerOnServer(String username, String password) {
        boolean OK = false;
        User user = user = new UserManager().createNewUser(username, password, null);
        DataPackage toSend = new DataPackage.PackageBuilder()
                .sender(user)
                .packageType(DataPackage.NEW_USER_REGISTRATION)
                .build();
        try {
            Socket socket = new Socket("localhost", 8079);
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            oos.writeObject(toSend);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            DataPackage recieved =(DataPackage) ois.readObject();
            if(recieved.getPackageType() == DataPackage.REGISTRATION_VERIFICATION) {
                OK = recieved.isVerificationSuccess();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return OK;
    }

    /**
     * @author Anna Håkansson
     *
     * method for sendig a login request to server. returns if login was ok. if it was ok,
     * the socket is saved for the client object.
     * @param username
     * @param password
     * @return if login was ok
     */
    public boolean logIn (String username, String password) {
        boolean OK = false;
        Socket socket;
        User user = user = new UserManager().createNewUser(username, password, null);
        DataPackage toSend = new DataPackage.PackageBuilder()
                .username(username)
                .password(password)
                .packageType(DataPackage.USER_LOGGED_IN)
                .build();
        try {
            socket = new Socket("localhost", 8079);
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            oos.writeObject(toSend);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            DataPackage recieved = (DataPackage) ois.readObject();
            if(recieved.getPackageType() == DataPackage.LOGIN_VERIFICATION) {
                OK = recieved.isVerificationSuccess();
                this.user = recieved.getUserFromServer();
                this.projects = recieved.getProjectList();
                for(Project project : projects) {
                    System.out.println(project.getProjectName());
                }
                client = new Client(user, socket, oos, ois);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return OK;
    }

    /**
     * @author Anna Håkansson
     *
     * Method for logging out: sending the userobject in a logoutpackage to server
     * and then disconnect client.
     */
    public void logOut () {
        System.out.println("Logging out");
        DataPackage logOutDataPackage = new DataPackage.PackageBuilder()
                .sender(user)
                .packageType(DataPackage.USER_LOGGED_OUT)
                .build();
        client.sendUpdate(logOutDataPackage);
        client.disconnect();
    }

    /**
     * @author Anna Håkansson
     * method for sending a newly created task to server
     * @param task
     */
    public void createTask(Model.Task task) {
        DataPackage toSend = new DataPackage.PackageBuilder()
                .task(task)
                .project(activeProject)
                .packageType(DataPackage.NEW_TASK)
                .build();
        client.sendUpdate(toSend);

    }

    /**
     * @author Anna Håkansson
     * method for sending an updated task to server
     * @param task
     */
    public void taskEdited (Model.Task task) {
        DataPackage toSend = new DataPackage.PackageBuilder()
                .task(task)
                .project(activeProject)
                .packageType(DataPackage.TASK_EDITED)
                .build();
        client.sendUpdate(toSend);
    }
    //// TODO: 2022-06-03 javadoc.
    public void changeProject (String projectID) {
        for (int i = 0; i < this.projects.size(); i++) {
            if (projectID.equals(projects.get(i).getProjectName())){
                activeProject = projects.get(i);
            }
        }
    }

    /**
     * @author Anna Håkansson
     * method for creating new project object and send it to server
     * @param header
     * @param description
     * @param deadline
     * @param assigneeList
     * @param creator
     */
    public void createNewProject(String header, String description, LocalDate deadline, ArrayList<String> assigneeList, String creator) {
        HashMap<String, Boolean> assignees = new HashMap<>();
        for (String user : assigneeList) {
            assignees.put(user, false);
        }
        assignees.put(creator, true);
        Project project = new Project.ProjectBuilder()
                    .projectName(header)
                    .description(description)
                    .deadline(deadline)
                    .createdDate(LocalDate.now())
                    .assignedUsers(assignees)
                    .build();
        DataPackage toSend = new DataPackage.PackageBuilder()
                .project(project)
                .packageType(DataPackage.NEW_PROJECT)
                .build();
        client.sendUpdate(toSend);
        System.out.println(project.getProjectName());
        if(activeProject == null) {
            this.activeProject = project;
        }
    }

    public ArrayList<Model.Task> getTask(){
         return activeProject.getTasks();
    }
    public int getTaskSize(){
        return activeProject.getTaskListSize();
    }

    /**
     * @author Anna Håkansson
     * method for unpacking a new datapackage from server
     * @param message
     */
    public void unpack(DataPackage message) {
        switch (message.getPackageType()) {
            case DataPackage.PROJECT_UPDATE:
                projectUpdate(message.getProject(), message.getTasks());
                break;
            case DataPackage.PROJECT_REMOVED:

                break;
        }
    }

    /**
     * @author Anna Håkansson
     *
     * method for handling a project update recieved from server
     * @param project
     * @param tasks
     */
    private void projectUpdate(Project project, Model.Task[] tasks) {
        System.out.println("project update in controller");
        boolean projectInList = false;
        ArrayList<Model.Task> newTaskList = new ArrayList<>();
        for(int i = 0; i < tasks.length; i++) {
            newTaskList.add(tasks[i]);
            System.out.println(i + tasks[i].getHeader());
        }
        project.setTaskList(newTaskList);
        for(int i = 0; i < projects.size(); i++) {
            if(project.getProjectID() == projects.get(i).getProjectID()) {
                projects.remove(i);
                projects.add(i, project);
                projectInList = true;
                if(this.activeProject == null || project.getProjectID() == this.activeProject.getProjectID()) {
                    this.activeProject = project;
                }
            }
        }
        if(!projectInList) {
            projects.add(project);
        }
        activeProject = project;
        System.out.println("Got update");
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public ArrayList<Project> getAllProject() {
        return projects;
    }
    //// TODO: 2022-06-03 javado eller ta bort?
    public ArrayList<String> getAllUsersInProject(String projectName) {
        ArrayList<String> projectUserList = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectName().equals(projectName)) {
                projectUserList.addAll(projects.get(i).getAssignedUser().keySet());
            }
        }
        return projectUserList;
    }

    public Project getActiveProject() {
        return activeProject;
    }

    public void setCurrenTaskID(int taskId) {
        this.currentTaskId = taskId;
    }

    /**
     * This methdo sets the latest position of a task in a swimlane, the position is determined on where the task
     * is dropped.
     * @param status Enum swimlane-type.
     */
    public void setLastSwimlanePosition(Swimlane status) {
        Model.Task task = activeProject.setNewTaskStatus(currentTaskId, status);
        System.out.println(task.getHeader());
        taskEdited(task);
        currentTaskId = -1;
    }
}
