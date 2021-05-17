package exercise.android.reemh.todo_items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    TodoItemsHolder itemsHolder;
    ClickCallBack lister;
    // private boolean onBind;          // TODO check if i need this field?

    public TodoItemAdapter(TodoItemsHolder holder, ClickCallBack clickCallBack){
        itemsHolder = holder;
        lister = clickCallBack;
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
        TodoItem todoItem = itemsHolder.getCurrentItems().get(position);
        holder.text.setText(todoItem.getTodoText());

        /*
        TODO: configure color etc.....
         */
    }

    @Override
    // recyclerView asks the adapter how much items it needs to render in total (including titles)
    public int getItemCount() {
        return itemsHolder.getCurrentItems().size();
    }
}
