package com.mystic.todolistapp;

import android.text.format.Time;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "Task_table")
public class Task {

    @PrimaryKey
    private UUID uuid;

    private String title;
    private String date ;
    private boolean done;

    public Task() {
        this.done = false;
        uuid = UUID.randomUUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getChecked() {
        return done;
    }

    public void setChecked(boolean done) {
        this.done = done;
    }
}
