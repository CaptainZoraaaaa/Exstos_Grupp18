package Model;

import java.util.HashMap;

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
