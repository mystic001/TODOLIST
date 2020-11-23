package com.mystic.todolistapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public static TaskDatabase taskDatabaseInstance;

    public static synchronized TaskDatabase getTaskDatabaseInstance(Context context){
        if(taskDatabaseInstance == null){
            taskDatabaseInstance = Room.databaseBuilder(context, TaskDatabase.class, "task_database")
                    .addCallback(roomCallback)
                    .build();
        }
        return taskDatabaseInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateBase(taskDatabaseInstance).execute();
        }
    };

    private static class PopulateBase extends AsyncTask<Void,Void,Void>{
        TaskDao taskDao;

        public  PopulateBase(TaskDatabase db){
            taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Task task = new Task();
            task.setTitle("Task1");
            task.setDate("12/03/2020");
            taskDao.insert(task);
            return null;
        }
    }


}
