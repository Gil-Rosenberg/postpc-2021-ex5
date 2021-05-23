package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
  // TODO: edit this class as you want
    private String todoText;
    private String creationTime;
    private int id;
    private boolean _isDone;
    private boolean _isInProgress;
    static int countAllId = 0;

    TodoItem(){
        id = countAllId;
        _isInProgress = true;
        _isDone = false;
        countAllId++;
    }

    public int getId(){
        return id;
    }

    public void setIdToNull(){
        id = -1;
    }

    public String getCreationTime() { return creationTime; }

    public void setCreationTime(String time) { creationTime = time; }

    public String getTodoText() { return todoText; }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public boolean isDone(){
        return _isDone;
    }

    public  boolean isInProgress(){
        return _isInProgress;
    }

    public void setToDone(){
        _isDone = true;
        _isInProgress = false;
    }

    public void setToInProgress(){
        _isDone = false;
        _isInProgress = true;
    }
}
