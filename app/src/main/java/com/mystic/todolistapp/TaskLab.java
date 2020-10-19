package com.mystic.todolistapp;

import java.util.ArrayList;
import java.util.List;

public class TaskLab {
    private static TaskLab sTaskLab;
    List<Task> mListTask ;
    private TaskLab() {
        mListTask = new ArrayList<>();

    }
    public static TaskLab get() {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab();
        }
        return sTaskLab;
    }


    public void addTask(Task task){
        mListTask.add(task);
    }

    public void removeTask(int position){
        mListTask.remove(position);
    }


    public List<Task> getTasks(){
        return mListTask;
    }

}

