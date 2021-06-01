package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class EditItemActivity extends AppCompatActivity {

    private TodoItemsHolderImpl dataBase = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_activity);

        if (dataBase == null){
            dataBase = TodoItemApplication.getInstance().getDataBase();
        }

        Intent intentOpenedMe = getIntent();
        String itemIdToEdit = intentOpenedMe.getStringExtra("itemToEdit");

        // finding the item we want to edit
        TodoItem itemToEdit = dataBase.getById(itemIdToEdit);

        // find all views:
        EditText description = findViewById(R.id.new_description);
        TextView creationTime = findViewById(R.id.time_stamp_creation_time);
        TextView lastModify = findViewById(R.id.time_stamp_last_modified);
        CheckBox checkBox = findViewById(R.id.checkBoxEdit);

        //set views with current data
        description.setText(itemToEdit.getDescription());
        creationTime.setText(itemToEdit.getCreationTime());
        lastModify.setText(itemToEdit.getLastModification());
        checkBox.setChecked(itemToEdit.isDone());


        // edit text:
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dataBase.editDescription(itemToEdit, description.getText().toString());
                lastModify.setText(itemToEdit.getLastModification());
            }
        });

        // edit checkBox:
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                dataBase.markItemDone(itemToEdit);
            }
            else {
                dataBase.markItemInProgress(itemToEdit);
            }
        });
    }
}
