package ServerSide;

import Model.Package;

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
    public void unpackNewPackage(ClientHandler clientHandler, Package newPackage) { //TODO javadoca och implementera samliga ordentligt
        switch (newPackage.getType()) {
            case Package.USER_LOGGED_IN:
                serverController.verifyCredentials(clientHandler, newPackage.getUsername(), newPackage.getPassword());
                break;
            case Package.NEW_USER_REGISTRATION:
                serverController.newRegistration(clientHandler, newPackage.getSender());
                break;
            case Package.USER_ASSIGNED_TO_PROJECT:
                serverController.userAssignedToProject(newPackage.getUsername(), newPackage.getProject());
                break;
            case Package.USER_REMOVED_FROM_PROJECT:
                serverController.userRemovedFromProject(newPackage.getSender(), newPackage.getProject());
                break;
            case Package.USER_DELETED:
                serverController.deleteUser(newPackage.getSender());
                break;
            case Package.USER_LOGGED_OUT:
                serverController.userLoggedOut(newPackage.getSender());
                break;
            case Package.NEW_TASK:
                serverController.newTask(newPackage.getTask(), newPackage.getProject());
                break;
            case Package.TASK_EDITED:
                serverController.taskEdited(newPackage.getTask(), newPackage.getProject());
                break;
            case Package.TASK_REMOVED:
                serverController.taskRemoved(newPackage.getTask(), newPackage.getProject());
                break;
            case Package.NEW_PROJECT:
                serverController.newProject(newPackage.getProject());
                break;
            case Package.PROJECT_EDITED:
                serverController.projectEdited(newPackage.getProject());
                break;
            case Package.PROJECT_REMOVED:
                serverController.projectRemoved(newPackage.getProject());
                break;
        }
    }
}
