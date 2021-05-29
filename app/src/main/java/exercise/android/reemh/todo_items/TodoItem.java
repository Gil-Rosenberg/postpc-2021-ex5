package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
    private final String id;
    private String description;
    private String creationTime;
    private boolean isCompleted;

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

    public void setCreationTime(String time) { creationTime = time; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void completeTask(){
        isCompleted = true;
    }

    public void restoreTask(){
        isCompleted = false;
    }

    public  boolean getCompleted(){
        return isCompleted;
    }

    public String serialize(){
        return description + "%#" + creationTime + "%#" + isCompleted + "%#" + id;
    }
}
