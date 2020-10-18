package com.mystic.todolistapp;

import android.text.format.Time;

import java.util.Date;

public class Task {

    private String title;
    private Date date ;
    private Time time;
    private boolean done;

    public Task() {
        this.done = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getChecked() {
        return done;
    }

    public void setChecked(boolean done) {
        this.done = done;
    }
}
