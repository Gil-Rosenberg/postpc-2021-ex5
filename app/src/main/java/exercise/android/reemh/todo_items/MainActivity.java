package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  TodoItemsHolderImpl dataBase = null;
  TodoItemAdapter adapter;
  private BroadcastReceiver receiverToChanges;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (dataBase == null){
      dataBase = TodoItemApplication.getInstance().getDataBase();
    }

    adapter = new TodoItemAdapter(dataBase);

    // find all views:
    EditText editTextInsertTask = findViewById(R.id.editTextInsertTask);
    FloatingActionButton buttonCreateTodoItem = findViewById(R.id.buttonCreateTodoItem);
    RecyclerView todoItemRecycler = findViewById(R.id.recyclerTodoItemsList);
    todoItemRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    todoItemRecycler.setAdapter(adapter);   // todoItemRecycler -> findViewById

    receiverToChanges = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("db_changed")){

          //todo: db was changed. get new list from the intent extra or from db.getCopies()
          // todo: refresh UI with new items
        }
      }
    };
    registerReceiver(receiverToChanges, new IntentFilter("db_changed"));

    buttonCreateTodoItem.setOnClickListener(v -> {
      if (!editTextInsertTask.getText().toString().equals("")){
        dataBase.addNewInProgressItem(editTextInsertTask.getText().toString());
        adapter.notifyDataSetChanged();
      }
      editTextInsertTask.setText("");
    });

    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        dataBase.getCurrentItems().remove(viewHolder.getAdapterPosition());
        adapter.notifyDataSetChanged();
      }
    };
    new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(todoItemRecycler);
  }


  // TODO: maybe replace with broadcast
  @Override
  protected void onResume(){
    super.onResume();
    List<TodoItem> newTodoItems = dataBase.getCopies();
    // todo: refresh UI with new items
  }

  @Override
  protected void onDestroy() {
    unregisterReceiver(receiverToChanges);
    super.onDestroy();
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("holder", dataBase);
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    dataBase = (TodoItemsHolderImpl) savedInstanceState.getSerializable("holder");
    adapter.setAdapterFields(dataBase);
    adapter.notifyDataSetChanged();
  }
}
