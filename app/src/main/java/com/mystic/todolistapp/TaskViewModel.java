package com.mystic.todolistapp;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskLab taskLab;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskLab = TaskLab.getInstance();
    }


    public LiveData<List<Task>> getLiveTasks() {
        return taskLab.getTasksLive();
    }

    public void delete(int pos){
        taskLab.removeTask(pos);
    }

    public void addtask(Task task){

        // i Stopped here;
        taskLab.addTask(task);
    }

}
