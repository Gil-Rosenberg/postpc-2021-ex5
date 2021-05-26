package exercise.android.reemh.todo_items;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TodoItemsHolderImpl implements TodoItemsHolder {
  
  private LinkedList<TodoItem> currentItems = new LinkedList<>();

  @Override
  public List<TodoItem> getCurrentItems() {
    return currentItems;
  }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem todoItem = new TodoItem();
    String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
    todoItem.setTodoText(description);
    todoItem.setCreationTime(date);
    currentItems.addFirst(todoItem);
  }

  @Override
  public void markItemDone(TodoItem item) {
    currentItems.remove(item);
    currentItems.addLast(item);
    item.completeTask();
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    item.restoreTask();
    currentItems.remove(item);
    currentItems.addFirst(item);
  }

  @Override
  public void deleteItem(TodoItem item) {
    currentItems.remove(item);
  }

  public void setItemProgress(int position, boolean isChecked) {
    if (isChecked){
      markItemDone(currentItems.get(position));
    }
    else {
      markItemInProgress(currentItems.get(position));
    }
  }
}
