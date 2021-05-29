package exercise.android.reemh.todo_items;

import android.app.Application;

/**
 * This is the Singleton
 */
public class TodoItemApplication extends Application {

    private TodoItemsHolderImpl dataBase;

    public TodoItemsHolderImpl getDataBase() {
        return dataBase;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        dataBase = new TodoItemsHolderImpl(this);
    }

    private static TodoItemApplication instance = null;

    public static TodoItemApplication getInstance() {
        return instance;
    }
}
