package Sandbox;

import Sandbox.testServer;
import ServerSide.Server;
/**
 * THIS CLASS IS DEPRECATED AND IS NOT USED IN THE PRODUCT.
 * WE CHOSE TO KEEP THE CLASSES WE DO NOT USE FOR THEIR VALUE
 * AS ARTEFACTS.
 * THEREFORE, THIS CLASS IS NOT INCLUDED IN THE CLASS DIAGRAM
 */

public class StartServ {
    public static void main(String[] args) {
        testServer server = new testServer(8080);
    }
}
