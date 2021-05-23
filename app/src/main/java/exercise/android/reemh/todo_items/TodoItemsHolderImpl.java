package exercise.android.reemh.todo_items;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class TodoItemsHolderImpl implements TodoItemsHolder {

  LinkedList<TodoItem> inProgressItems = new LinkedList<>();
  Vector<TodoItem> doneItems = new Vector<>();
  List<TodoItem> currentItems = new LinkedList<>();

  @Override
  public List<TodoItem> getCurrentItems() {
    currentItems.clear();
    currentItems.addAll(inProgressItems);
    currentItems.addAll(doneItems);
    return currentItems;
  }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem todoItem = new TodoItem();
    String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
    todoItem.setTodoText(description);
    todoItem.setCreationTime(date);
    inProgressItems.addFirst(todoItem);
  }

  @Override
  public void markItemDone(TodoItem item) {
    if (item.getId() == -1){
      return;
    }

    inProgressItems.remove(item);
    doneItems.add(item);
    item.setToDone();
  }

  // @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  public void markItemInProgress(TodoItem item) {
    if (item.getId() == -1){
      return;
    }

    doneItems.remove(item);
    inProgressItems.add(item);
    Collections.sort(inProgressItems, (o1, o2) -> {
      return o1.getId() - o2.getId();   // todo make sure it is the right order
    });
    item.setToInProgress();
  }

  @Override
  public void deleteItem(TodoItem item) {
    if (item.getId() == -1){
      return;
    }

    if (inProgressItems.contains(item)){
      inProgressItems.remove(item);
    }
    else doneItems.remove(item);
    currentItems.remove(item);
    item.setIdToNull();
  }

  public LinkedList<TodoItem> getInProgressItems(){
    return inProgressItems;
  }

  public Vector<TodoItem> getDoneItems(){
    return doneItems;
  }

}
