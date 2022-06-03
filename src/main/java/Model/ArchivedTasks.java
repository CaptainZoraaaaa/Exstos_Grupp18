package Model;
/**
 * THIS CLASS IS DEPRECATED AND IS NOT USED IN THE PRODUCT.
 * WE CHOSE TO KEEP THE CLASSES WE DO NOT USE FOR THEIR VALUE
 * AS ARTEFACTS.
 * THEREFORE, THIS CLASS IS NOT INCLUDED IN THE CLASS DIAGRAM
 */
import java.util.HashMap;
/// TODO: 2022-05-20 RADERA 
public class ArchivedTasks {
    private HashMap<Task, Boolean> archive;
    public ArchivedTasks() {
        archive = new HashMap<Task, Boolean>();
    }

    public HashMap<Task, Boolean> getArchive() {
        return archive;
    }

    public void setArchive(HashMap<Task, Boolean> archive) {
        this.archive = archive;
    }
}
