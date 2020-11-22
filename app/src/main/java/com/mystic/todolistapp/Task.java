package com.mystic.todolistapp;

import android.text.format.Time;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "Task_table")
public class Task {

    @PrimaryKey
    @NonNull
    private String uuid;

    private String title;
    private String date ;
    private boolean done;

    public Task() {
        this.done = false;
        uuid = UUID.randomUUID().toString();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
