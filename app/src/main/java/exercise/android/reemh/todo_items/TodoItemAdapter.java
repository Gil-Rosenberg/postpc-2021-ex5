package exercise.android.reemh.todo_items;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    List<TodoItem> todoItemList;
    TodoItemsHolderImpl todoItemsHolder;
    private boolean onBind;

    TodoItemAdapter(TodoItemsHolderImpl holder){
        todoItemsHolder = holder;
        todoItemList = holder.getCurrentItems();
        onBind = false;
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();  // Access to Android resources
        View view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false);

        return new TodoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        onBind = true;
        TodoItem todoItem = todoItemList.get(position);

        if (todoItem.getCompleted()){
            holder.getText().setPaintFlags(holder.getText().getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        else{
            holder.getText().setPaintFlags(holder.getText().getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        // prepare description text:
        holder.getText().setText(todoItem.getTodoText());

        // prepare date text:
        holder.getDateTimeDisplay().setText(todoItem.getCreationTime());

        // init checkBox state:
        holder.getCheckBox().setChecked(todoItem.getCompleted());

        // listener for checkBox:
        holder.getCheckBox().setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!onBind){
                todoItemsHolder.setItemProgress(position, isChecked);
                notifyDataSetChanged();
            }
        });
        onBind = false;
    }

    @Override
    public int getItemCount() {
        return todoItemList.size();
    }

    public void setTodoItemList(List<TodoItem> currentItems){
        todoItemList.clear();
        todoItemList.addAll(currentItems);
    }
}
