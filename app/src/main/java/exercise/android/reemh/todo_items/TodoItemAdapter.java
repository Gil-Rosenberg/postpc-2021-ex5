package exercise.android.reemh.todo_items;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    List<TodoItem> todoItemList = new LinkedList<>();
    TodoItemsHolderImpl todoItemsHolder;
    private boolean onBind;

    TodoItemAdapter(TodoItemsHolderImpl holder){
        todoItemsHolder = holder;
        setTodo(holder.getCurrentItems());
        onBind = false;
    }

    @NonNull
    @Override
    // here we create view holder
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();  // Access to Android resources
        View view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false);

        return new TodoItemViewHolder(view);
    }

    @Override
    // here we need to configure the view holder
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        onBind = true;
        TodoItem todoItem = todoItemList.get(position);

        if (todoItem.isDone()){
            holder.getText().setPaintFlags(holder.getText().getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        else if (todoItem.isInProgress()){
            holder.getText().setPaintFlags(holder.getText().getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        holder.getText().setText(todoItem.getTodoText());
        holder.getDateTimeDisplay().setText(todoItem.getCreationTime());

        holder.getCheckBox().setChecked(!holder.getCheckBox().isChecked());
        // set listeners:
        holder.getCheckBox().setOnClickListener(v -> {
            if (!onBind){
                if (todoItemsHolder.getInProgressItems().contains(todoItem)){
                    todoItemsHolder.markItemDone(todoItem);
                }

                else if (todoItemsHolder.getDoneItems().contains(todoItem)){
                    todoItemsHolder.markItemInProgress(todoItem);
                }

                setTodo(todoItemsHolder.getCurrentItems());
            }
        });

        // TODO listener for deleting
        if (!onBind){
        }

        onBind = false;
    }

    @Override
    // recyclerView asks the adapter how much items it needs to render in total (including titles)
    public int getItemCount() {
        return todoItemList.size();
    }

    public void setTodo(List<TodoItem> newList){
        if (!todoItemList.isEmpty()){
            todoItemList.clear();
        }
        todoItemList.addAll(newList);
        notifyDataSetChanged();
    }
}
