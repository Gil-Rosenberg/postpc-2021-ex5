package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

  TodoItemsHolderImpl dataBase = null;
  TodoItemAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (dataBase == null){
      dataBase = TodoItemApplication.getInstance().getDataBase();
    }
    adapter = new TodoItemAdapter(dataBase);

    dataBase.itemsLiveDataPublic.observe(this, todoItems -> {
      if (todoItems != null){
        adapter.setAdapterFields(dataBase.getCopies());
      }
    });

    // find all views:
    EditText editTextInsertTask = findViewById(R.id.editTextInsertTask);
    FloatingActionButton buttonCreateTodoItem = findViewById(R.id.buttonCreateTodoItem);
    RecyclerView todoItemRecycler = findViewById(R.id.recyclerTodoItemsList);
    todoItemRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//    adapter.setAdapterFields(dataBase.getCurrentItems()); // todo not sure??
    todoItemRecycler.setAdapter(adapter);

    buttonCreateTodoItem.setOnClickListener(v -> {
      if (!editTextInsertTask.getText().toString().equals("")){
        dataBase.addNewInProgressItem(editTextInsertTask.getText().toString());
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
        int index = viewHolder.getAdapterPosition();
        dataBase.deleteItem(dataBase.getCurrentItems().get(index));
        // dataBase.getCurrentItems().remove(viewHolder.getAdapterPosition()); // todo maybe put this back
        //adapter.setAdapterFields(dataBase.getCurrentItems());
      }
    };
    new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(todoItemRecycler);
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
    adapter.setAdapterFields(dataBase.getCurrentItems());
  }
}
