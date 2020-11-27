package com.mystic.todolistapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public static TaskDatabase taskDatabaseInstance;

    public static synchronized TaskDatabase getTaskDatabaseInstance(Context context){
        if(taskDatabaseInstance == null){
            taskDatabaseInstance = Room.databaseBuilder(context, TaskDatabase.class, "task_database")
                    .build();
        }
        return taskDatabaseInstance;
    }


}
