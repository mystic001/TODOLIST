package com.mystic.todolistapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task_table")
    LiveData<List<Task>> getAllTasks();

}
