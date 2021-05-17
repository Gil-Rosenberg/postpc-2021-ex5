package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemViewHolder extends RecyclerView.ViewHolder {

    CheckBox checkBox;
    TextView text;
    TextView creationTime;

    public TodoItemViewHolder(@NonNull View itemView) {    // View itemView = row_todo_item.xml
        super(itemView);
        // TODO put here itemView.findViewById() of all relevant sub-views in itemView
        CheckBox checkBox = itemView.findViewById(R.id.checkBox);
        TextView text = itemView.findViewById(R.id.itemText);
        TextView creationTime = itemView.findViewById(R.id.creationTime);
    }



}
