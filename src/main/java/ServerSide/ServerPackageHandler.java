/*package ServerSide;

import Model.Package;

/**
 * @author Anna Håkansson
 *
 */
/*
public class ServerPackageHandler {
    private ServerController serverController;

    public ServerPackageHandler(ServerController serverController) {
        this.serverController = serverController;
    }
    public void unpackNewPackage(ClientHandler clientHandler, Package newPackage) { //TODO javadoca och implementera samliga ordentligt
        switch (newPackage.getType()) {
            case Package.USER_LOGGED_IN:
                server.addOnlineUser(newPackage.getSender());
                server.addClient(newPackage.getSender().getUsername(), clientHandler);
                break;
            case Package.NEW_USER_REGISTRATION:
                serverController.verifyRegistration(clientHandler, newPackage.getSender());
                break;
            case Package.USER_ASSIGNED_TO_PROJECT:
                server.addUserToProject(newPackage.getUsername(), newPackage.getProject());
                break;
            case Package.USER_REMOVED_FROM_PROJECT:
                server.removeUserFromProject(newPackage.getSender(), newPackage.getProject());
                break;
            case Package.USER_DELETED:
                serverController.deleteUser(newPackage.getSender());
                break;
            case Package.USER_LOGGED_OUT:
                server.removeOnlineUser(newPackage.getSender());
                break;
            case Package.NEW_TASK:
                server.addTaskToProject(newPackage.getTasks(), newPackage.getProject());
                break;
            case Package.TASK_EDITED:
                server.updateTask(newPackage.getTasks(), newPackage.getProject());
                break;
            case Package.TASK_REMOVED:
                server.removeTask(newPackage.getTasks(), newPackage.getProject());
                break;
            case Package.NEW_PROJECT:
                server.addProject(newPackage.getProject());
                break;
            case Package.PROJECT_EDITED:
                server.updateProject(newPackage.getProject());
                break;
            case Package.PROJECT_REMOVED:
                server.deleteProject(newPackage.getProject());
                break;
            case Package.LOGIN_VERIFICATION:
                serverController.verifyCredentials(clientHandler, newPackage.getUsername(), newPackage.getPassword());

        }
    }
} */
