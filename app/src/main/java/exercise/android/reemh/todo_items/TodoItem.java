package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
    private String todoText;
    private String creationTime;
    private boolean isCompleted;

    TodoItem(){
        isCompleted = false;
    }

    public String getCreationTime() { return creationTime; }

    public void setCreationTime(String time) { creationTime = time; }

    public String getTodoText() { return todoText; }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
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
}
