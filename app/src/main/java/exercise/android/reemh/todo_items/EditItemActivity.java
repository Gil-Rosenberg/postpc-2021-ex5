package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class EditItemActivity extends AppCompatActivity {

    private TodoItemsHolderImpl dataBase = null;
    private boolean dataChanged = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_activity);

        // find all views:
        EditText editDescription = findViewById(R.id.new_description);
        TextView creationTime = findViewById(R.id.time_stamp_creation_time);
        TextView lastModified = findViewById(R.id.time_stamp_last_modified);
        CheckBox checkBox = findViewById(R.id.checkBoxEdit);

        if (dataBase == null){
            dataBase = TodoItemApplication.getInstance().getDataBase();
        }

        Intent intentOpenedMe = getIntent();
        TodoItem itemToEdit = (TodoItem) intentOpenedMe.getSerializableExtra("itemToEdit");

        // current text before change:
        editDescription.setText(itemToEdit.getDescription());

        // set creation time:
        creationTime.setText(itemToEdit.getCreationTime());

        // edit text:
        editDescription.setOnClickListener(v -> {
            dataChanged = true;
            itemToEdit.setDescription(editDescription.getText().toString());
        });

        // edit state:
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            dataChanged = true;
            dataBase.setItemProgress(itemToEdit, isChecked);
        });

        if (dataChanged){

            dataChanged = false;
        }


    }
}
