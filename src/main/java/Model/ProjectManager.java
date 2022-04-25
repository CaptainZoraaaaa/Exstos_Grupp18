package Model;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author Anna Håkansson
 * @version 1.0
 *
 * Last update: 22-04-14
 *
 * Class for managing project related functions, such as
 * creating, editing, input-check and managing users
 * assigned to the current project.
 * hej
 */
public class ProjectManager {
    private Project currentProject;

    public void addProject(Project project) {
        //TODO i think the idea for this method came before we decided to not have an arraylist of projects, should it be removed?
    }

    /**
     * @author Anna Håkansson
     *
     * @param name
     * @param description
     * @param deadline
     * @param userAdmin
     * @return the project that was build
     *
     * method for creating project-objects by using a builder. Returns a project if the input
     * is correct.
     */
    public Project createProject(String name, String description, LocalDate deadline, User userAdmin) {
        if (name != null && description != null && deadline != null
        && name != "" && description != "") { //check that the variables aren't null or empty
            Project project = new Project.ProjectBuilder() //initiate object and call on ProjectBuilder
                    .projectName(name) //the name to be set
                    .description(description)//the description to be set
                    .deadline(deadline)//the deadline to be set
                    .userAdmin(userAdmin) //the user that creates the project thus becoming project admin
                    .build(); //builds the thing
            return project; //returns the project that was created
        } else {
            //TODO error message
            return null;
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param isAdmin if the user is admin or not
     * @param newName the new name for the project
     *
     * Method for editing the project name.
     */
    public void editProjectName(boolean isAdmin, String newName) {
        if (isAdmin && newName != null && newName != "") { //if the user is admin and the new name meets the input criterias
            currentProject.setProjectName(newName); //set the name in current project object
        }
        else {
            //TODO error message or return boolean
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param isAdmin if the user is admin or not
     * @param newDescription the new description for the project
     *
     * Method for editing the project name.
     */
    public void editProjectDescription(boolean isAdmin, String newDescription) {
        if (isAdmin && newDescription != null && newDescription != "") { //if the user is admin and the new description meets the input criterias
            currentProject.setDescription(newDescription); //set the description in the current project object
        } else {
            //TODO error message or return boolean
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param isAdmin if the user is admin or not
     * @param newDate the new deadline for the project
     *
     * Method for editing the project name.
     */
    public void editProjectDeadline(boolean isAdmin, LocalDate newDate) {
        if(isAdmin && newDate != null) { //if the user is admin and the new date isn't null
            currentProject.setDeadline(newDate); //set the new deadline in current project
        }
        else {
            //TODO error message or return boolean
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param text the string that need to be checked
     * @param maxLength the max number of characters the string should have
     * @return if the number of characters was bigger than maxLength or not
     *
     * Method for checking the length of a string compared to the max number of characters it should have
     */
    public boolean checkLength(String text, int maxLength) {
        if(text.length() <= maxLength) { //if the length of the text is smaller or equal to the assigned max length
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @author Anna Håkansson
     *
     * @param isAdmin if the user is project admin or not
     * @param newOwner the new owner of the
     * @param oldOwner the old owner of the project (e.g. the current user)
     *
     * Method for changing the owner of a project. Can only be done by project owner.
     */
    public void changeOwner(boolean isAdmin, User newOwner, User oldOwner) { //TODO added oldOwner
        HashMap<User, Boolean> assignees = currentProject.getAssignedUser();
        if (assignees.get(oldOwner)) {
            assignees.replace(oldOwner, true, false);
            assignees.replace(newOwner, false, true);
        }
    }

    /**
     * @author Anna Håkansson
     * @param user the user to be added to the project
     *
     * Method for assigning a user to a project.
     */
    public void addUser(User user) {
        if (user != null) { //if user isnt null
            HashMap<User, Boolean> assignees = currentProject.getAssignedUser(); //get the assignedUsers hashmap and store in temporary variable
            assignees.put(user, false); //put the user in the hashmap with false admin-value
            currentProject.setAssignedUser(assignees); //set the hashmap in project
        }
        else {
            //TODO error message
        }
    }

    /**
     * @param user the user to be removed from a project
     *
     * Method for removing a user from current project.
     */
    public void removeUser(User user) {
        HashMap<User, Boolean> assignees = currentProject.getAssignedUser(); //get the assignedUsers hashmap and store in temporary variable
        if (!assignees.get(user)) { //if the boolean admin-value for the user isn't true
            assignees.remove(user, false); //remove from hashmap
            currentProject.setAssignedUser(assignees); //set the hashmap in projekt
        }
        else {
            //TODO error message + behöver vi isAdmin-boolean?
        }
    }
}
