package Model;

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
