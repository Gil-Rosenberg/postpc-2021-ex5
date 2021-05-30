package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
    private final String id;
    private String description;
    private String timeAsText;
    private final boolean isCompleted;
    private long doneTime = -1L;
    private final long creationTime = System.currentTimeMillis();

    public TodoItem(String id, String description, String time, boolean flag){
        this.id = id;
        this.description = description;
        this.timeAsText = time;
        this.isCompleted = flag;
    }

    public String getId() {
        return id;
    }

    public String getTimeAsText() { return timeAsText; }

    public void setTimeAsText(String time) { timeAsText = time; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public  boolean getCompleted(){
        return isCompleted;
    }

    public String serialize(){
        return description + "%#" + timeAsText + "%#" + isCompleted + "%#" + id;
    }
}
