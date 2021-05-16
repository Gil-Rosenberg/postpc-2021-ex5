package exercise.android.reemh.todo_items;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    /*
    TODO:   - maybe put here private list of all todoItems
            - setItems(new list) { clear current list, addAll new list, notifyDataSetChanged() -> to the recyclerView }
            - ClickCallBack -> listener
     */

    @NonNull
    @Override

    // here we create view holder
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
         TODO:  context = parent.context (Access to Android resources)
                view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false)
                return new TodoItemViewHolder(view)
         */
        return null;
    }

    @Override

    // here we need to configure the view holder
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        /*
        TODO:   todoItem = todoItemsList[todoItem]
                holder.text.setText(todoItem.text)  // configure text
                holder.img.setImageResource         // configure img (if we have one)
                configure color etc.....
         */
    }

    @Override

    // recyclerView asks the adapter how much items it needs to render in total (including titles)
    public int getItemCount() {
        /*
        TODO return todoItemsList.size()
         */
        return 0;
    }
}
