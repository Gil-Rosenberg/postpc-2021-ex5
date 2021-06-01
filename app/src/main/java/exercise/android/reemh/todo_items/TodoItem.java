package exercise.android.reemh.todo_items;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TodoItem implements Serializable {
    private final String id;
    private String description;
    private final String creationTime;
    private final boolean isDone;
    private String lastModification;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TodoItem(String id, String description, String time, boolean flag){
        this.id = id;
        this.description = description;
        this.creationTime = time;
        this.isDone = flag;
        this.lastModification = LocalDateTime.now().toString();
    }

    public String getId() {
        return id;
    }

    public String getCreationTime() { return creationTime; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone(){
        return isDone;
    }

    public String ItemToString(){
        return description + "%#" + creationTime + "%#" + isDone + "%#" + id + "%#" + lastModification;
    }

    public void setLastModification(String newModification) {
        lastModification = newModification;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getLastModification() {
        String currentDateTime = LocalDateTime.now().toString();

        String[] splitDateTime = currentDateTime.split("T");
        String[] splitModifiedDateTime = lastModification.split("T");

        String date = splitDateTime[0];
        String ModifiedDate = splitModifiedDateTime[0];

        String[] timeSplit = splitDateTime[1].split(":");
        String[] modifiedTimeSplit = splitModifiedDateTime[1].split(":");

        Integer hour = Integer.valueOf(timeSplit[0]);
        Integer modifiedHour = Integer.valueOf(modifiedTimeSplit[0]);

        Integer minute = Integer.valueOf(timeSplit[1]);
        Integer modifiedMinute = Integer.valueOf(modifiedTimeSplit[1]);

        // calculate last -modified
        if (date.equals(ModifiedDate)){

            int diffHour = hour - modifiedHour;

            // last-modified time was less than a hour ago, the text should be "<minutes> minutes ago"
            if (diffHour == 0){
                int diffMinutes = minute - modifiedMinute;
                return "Last modification: " + diffMinutes + " minutes ago";
            }

            // last-modified time was earlier than a hour, but today, the text should be "Today at <hour>"
            return "Today at " + modifiedHour;
        }

        // last-modified time was yesterday or earlier, the text should be "<date> at <hour>"
        return ModifiedDate + " at " + modifiedHour;
    }

    public String creationTimeForEditScreen(){
        String[] split = this.creationTime.split("T");
        return split[0] + " at " + split[1];
    }
}
