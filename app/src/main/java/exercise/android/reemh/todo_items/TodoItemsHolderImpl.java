package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TodoItemsHolderImpl implements TodoItemsHolder {
  
  private final LinkedList<TodoItem> currentItems = new LinkedList<>();
  private final Context context;
  private final SharedPreferences sp;

  public TodoItemsHolderImpl(Context context){
    this.context = context;
    this.sp = context.getSharedPreferences("local_db_items", Context.MODE_PRIVATE);
    initializeFromSp();
  }

  private void initializeFromSp(){
    Set<String> keys = sp.getAll().keySet();
    for (String key : keys) {
      String itemSavedAsString = sp.getString(key, null);
      TodoItem item = stringToItem(itemSavedAsString);
      currentItems.addFirst(item);
    }
  }

  @Override
  public List<TodoItem> getCurrentItems() {
    return currentItems;
  }

  @Override
  public void addNewInProgressItem(String description) {
    String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
    String id = UUID.randomUUID().toString();
    TodoItem todoItem = new TodoItem(id, description, date, false);
    currentItems.addFirst(todoItem);

    SharedPreferences.Editor editor = sp.edit();
    editor.putString(todoItem.getId(), todoItem.serialize());
    editor.apply();

    sendBroadcastDbChanged();
  }

  @Override
  public void markItemDone(TodoItem item) {
    currentItems.remove(item);
    currentItems.addLast(item);
    item.completeTask();

    sendBroadcastDbChanged();
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    item.restoreTask();
    currentItems.remove(item);
    currentItems.addFirst(item);

    sendBroadcastDbChanged();
  }

  @Override
  public void deleteItem(TodoItem item) {
    currentItems.remove(item);

    SharedPreferences.Editor editor = sp.edit();
    editor.remove(item.getId());
    editor.apply();

    sendBroadcastDbChanged();
  }

  @Override
  public void setItemProgress(int position, boolean isChecked) {
    if (isChecked){
      markItemDone(currentItems.get(position));
    }
    else {
      markItemInProgress(currentItems.get(position));
    }

    sendBroadcastDbChanged();
  }

  @Override
  public void editDescription(String id, String newDescription){
    TodoItem oldItem = getItemById(id);
    if (oldItem == null) return;
    String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
    TodoItem newItem = new TodoItem(id, newDescription, date, oldItem.getCompleted());
    currentItems.remove(oldItem);
    currentItems.addFirst(newItem);

    SharedPreferences.Editor editor = sp.edit();
    editor.putString(newItem.getId(), newItem.serialize());
    editor.apply();

    sendBroadcastDbChanged();
  }

  @Override
  public List<TodoItem> getCopies(){
    return new LinkedList<>(currentItems);
  }

  private void sendBroadcastDbChanged(){
    Intent broadcast = new Intent("db_changed");
    broadcast.putExtra("new_list", (Parcelable) getCopies()); //todo: Parcelable ??
    context.sendBroadcast(broadcast);
  }

  private @Nullable TodoItem getItemById(String id){
    TodoItem todoItem = null;

    for (TodoItem item:currentItems) {
      if (item.getId().equals(id)){
        todoItem = item;
      }
    }
    return todoItem;
  }

  @Override
  public TodoItem stringToItem(String string){
    if (string == null) return null;
    try {
      List<String> split = Arrays.asList(string.split("%#"));
      String description = split.get(0);
      String time = split.get(1);
      boolean isCompleted = Boolean.getBoolean(split.get(2));
      String id = split.get(3);
      return new TodoItem(id, description, time, isCompleted);
    }catch (Exception e){
      System.err.println("exception. input: " + string + ".\n" + "exception: " + e);
      return null;
    }
  }
}
