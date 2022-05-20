package ServerSide;

import Model.DataPackage;

/**
 * @author Anna Håkansson
 * last update: 2022-05-11
 *
 * A class for handling packages on the serverside, sending them to the servercontroller
 * where the corresponding actions are taken.
 *
 */
public class ServerPackageHandler {
    private ServerController serverController;

    public ServerPackageHandler(ServerController serverController) {
        this.serverController = serverController;
    }
    public void unpackNewPackage(ClientHandler clientHandler, DataPackage newDataPackage) { //TODO javadoca och implementera samliga ordentligt
        switch (newDataPackage.getPackageType()) {
            case DataPackage.USER_LOGGED_IN:
                serverController.verifyCredentials(clientHandler, newDataPackage.getUsername(), newDataPackage.getPassword());
                break;
            case DataPackage.NEW_USER_REGISTRATION:
                serverController.newRegistration(clientHandler, newDataPackage.getSender());
                break;
            case DataPackage.USER_ASSIGNED_TO_PROJECT:
                serverController.userAssignedToProject(newDataPackage.getUsername(), newDataPackage.getProject());
                break;
            case DataPackage.USER_REMOVED_FROM_PROJECT:
                serverController.userRemovedFromProject(newDataPackage.getSender(), newDataPackage.getProject());
                break;
            case DataPackage.USER_DELETED:
                serverController.deleteUser(newDataPackage.getSender());
                break;
            case DataPackage.USER_LOGGED_OUT:
                serverController.userLoggedOut(newDataPackage.getSender());
                break;
            case DataPackage.NEW_TASK:
                serverController.newTask(newDataPackage.getTask(), newDataPackage.getProject());
                break;
            case DataPackage.TASK_EDITED:
                serverController.taskEdited(newDataPackage.getTask(), newDataPackage.getProject());
                break;
            case DataPackage.TASK_REMOVED:
                serverController.taskRemoved(newDataPackage.getTask(), newDataPackage.getProject());
                break;
            case DataPackage.NEW_PROJECT:
                serverController.newProject(newDataPackage.getProject());
                break;
            case DataPackage.PROJECT_EDITED:
                serverController.projectEdited(newDataPackage.getProject());
                break;
            case DataPackage.PROJECT_REMOVED:
                serverController.projectRemoved(newDataPackage.getProject());
                break;
        }
    }
}
