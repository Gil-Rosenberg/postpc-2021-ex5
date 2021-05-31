package exercise.android.reemh.todo_items;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemViewHolder extends RecyclerView.ViewHolder {

    private final CheckBox checkBox;
    private final TextView description;
    private final TextView dateTimeDisplay;

    @SuppressLint("SimpleDateFormat")
    public TodoItemViewHolder(@NonNull View itemView) {    // View itemView = row_todo_item.xml
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkBox);
        description = itemView.findViewById(R.id.itemText);
        dateTimeDisplay = itemView.findViewById(R.id.creationTime);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getDateTimeDisplay() {
        return dateTimeDisplay;
    }
}
