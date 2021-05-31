package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class EditItemActivity extends AppCompatActivity {

    public TodoItemsHolderImpl dataBase = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_activity);

        if (dataBase == null){
            dataBase = TodoItemApplication.getInstance().getDataBase();
        }

        Intent intentOpenedMe = getIntent();
        TodoItem itemToEdit = (TodoItem) intentOpenedMe.getSerializableExtra("itemToEdit");
        // TODO work with the UI, use the item from the intent

    }
}
