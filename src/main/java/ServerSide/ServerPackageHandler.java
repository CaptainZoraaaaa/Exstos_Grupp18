package ServerSide;

import Model.Package;

public class ServerPackageHandler {
    private Server server;
    private ServerBuffer<Package> serverBuffer;

    public ServerPackageHandler(Server server, ServerBuffer<Package> serverBuffer) {
        this.server = server;
        this.serverBuffer = serverBuffer;
    }
    public void unpackNewPackage(ClientHandler clientHandler, Package newPackage) { //TODO javadoca och implementera samliga ordentligt
        switch (newPackage.getType()) {
            case Package.USER_LOGGED_IN:
                server.addOnlineUser(newPackage.getSender());
                server.addClient(newPackage.getSender().getUsername(), clientHandler);
                break;
            case Package.NEW_USER_REGISTRATION:
                boolean registrationOK = server.newUserRegistration(newPackage.getSender());
                Package reply = new Package.PackageBuilder().ok(registrationOK).type(Package.REGISTRATION_VERIFICATION).build();
                clientHandler.sendMessage(reply);
                break;
            case Package.USER_ASSIGNED_TO_PROJECT:
                server.addUserToProject(newPackage.getUsername(), newPackage.getProject());
                break;
            case Package.USER_REMOVED_FROM_PROJECT:
                server.removeUserFromProject(newPackage.getSender(), newPackage.getProject());
                break;
            case Package.USER_DELETED:
                server.deleteUser(newPackage.getSender());
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
                server.verifyCredentials(newPackage.getUsername(), newPackage.getPassword());
                boolean loginOK = server.newUserRegistration(newPackage.getSender());
                Package loginReply = new Package.PackageBuilder().ok(loginOK).type(Package.LOGIN_VERIFICATION).build();
                clientHandler.sendMessage(loginReply);
        }
    }
}
