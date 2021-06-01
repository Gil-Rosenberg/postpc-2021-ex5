package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
    private final String id;
    private String description;
    private String creationTime;
    private final boolean isCompleted;
    // private long doneTime = -1L;
    private long LastModified = 0;

    public TodoItem(String id, String description, String time, boolean flag){
        this.id = id;
        this.description = description;
        this.creationTime = time;
        this.isCompleted = flag;
    }

    public String getId() {
        return id;
    }

    public String getCreationTime() { return creationTime; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public  boolean isCompleted(){
        return isCompleted;
    }

    public long getLastModified() {
        return LastModified = System.currentTimeMillis();
    }

    public String serialize(){
        return description + "%#" + creationTime + "%#" + isCompleted + "%#" + id;
    }
}
