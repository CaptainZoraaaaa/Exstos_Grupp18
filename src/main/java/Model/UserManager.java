package Model;

import controller.Controller;
import javafx.scene.image.Image;


/**
 * THIS CLASS IS DEPRECATED AND IS NOT USED IN THE PRODUCT.
 * WE CHOSE TO KEEP THE CLASSES WE DO NOT USE FOR THEIR VALUE
 * AS ARTEFACTS.
 * THEREFORE, THIS CLASS IS NOT INCLUDED IN THE CLASS DIAGRAM
 */


/**
 * @author Anna Håkansson
 * @version 1.0
 *
 * Last updated: 2022-04-14
 *
 * Class for managing user functionality
 */
public class UserManager {
    private User user; //current user
    private Controller controller = Controller.getInstance(); //controller

    /**
     * @author Anna Håkansson
     *
     * @param username the selected username
     * @param password the selected password
     * @param image the users profile picture
     * @return the new userObject
     *
     * Method for create new user with a UserBuilder.
     */
    public User createNewUser(String username, String password, Image image) {
        //TODO check in server if username doesn't exist
        User user = null;
        if(username != null && username != "" && password != null && password != "") { //if username and password isnt null or empty string
            user = new User.UserBuilder() //make new user with builder
                    .username(username)
                    .password(password)
                    .build();
        }
        return user; //return the new user
    }
}
