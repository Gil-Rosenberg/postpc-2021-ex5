package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
  // TODO: edit this class as you want
    private boolean isDone;
    private String todoText;
    private String creationTime;

    TodoItem(){
        isDone = false;
        todoText = "";
        creationTime = "";
    }

    public boolean isDone() { return isDone; }
    public void setIsDone() { isDone = !isDone; }

    public String getCreationTime() { return creationTime; }
    public void setCreationTime(String time) { creationTime = time; }

    public String getTodoText() { return todoText; }
    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }
}
