package com.mystic.todolistapp;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskLab taskLab;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskLab = TaskLab.getsTaskLab(application);
        allTasks = taskLab.getAllTasks();
    }


    public LiveData<List<Task>> getLiveTasks() {
        return allTasks;
    }

    public void delete(Task task){
        taskLab.removeTask(task);
    }

    public void addtask(Task task){
        // i Stopped here;
        taskLab.addTask(task);
    }


}
