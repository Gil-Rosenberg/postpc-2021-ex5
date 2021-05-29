package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


// todo need to sent in the intent the item
public class EditItemActivity extends AppCompatActivity {

    public TodoItemsHolderImpl dataBase = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (dataBase == null){
            dataBase = TotoItemApplication.getInstance().getDataBase();
        }

        Intent intentOpenedMe = getIntent();
        // TODO work with the UI, use the item from the intent

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
    }

}
