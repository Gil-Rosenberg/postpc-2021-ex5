package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

  TodoItemsHolderImpl holder = null;
  TodoItemAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (holder == null){
      holder = new TodoItemsHolderImpl();
    }
    adapter = new TodoItemAdapter(holder);

    // find all views:
    EditText editTextInsertTask = findViewById(R.id.editTextInsertTask);
    FloatingActionButton buttonCreateTodoItem = findViewById(R.id.buttonCreateTodoItem);
    RecyclerView todoItemRecycler = findViewById(R.id.recyclerTodoItemsList);
    todoItemRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    todoItemRecycler.setAdapter(adapter);   // todoItemRecycler -> findViewById

    buttonCreateTodoItem.setOnClickListener(v -> {
      if (!editTextInsertTask.getText().toString().equals("")){
        holder.addNewInProgressItem(editTextInsertTask.getText().toString());
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
        holder.getCurrentItems().remove(viewHolder.getAdapterPosition());
        adapter.notifyDataSetChanged();
      }
    };
    new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(todoItemRecycler);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("holder", holder);
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    holder = (TodoItemsHolderImpl) savedInstanceState.getSerializable("holder");
    adapter.setAdapterFields(holder);
    adapter.notifyDataSetChanged();
  }
}
