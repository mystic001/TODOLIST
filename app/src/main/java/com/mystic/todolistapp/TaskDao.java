package com.mystic.todolistapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task_table")
    LiveData<List<Task>> getAllTasks();

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

}
