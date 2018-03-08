package com.example.albin.forgetmenot.database;

/**
 * Created by albin on 11/11/16.
 */

public class TimerModel {
    public String title;
    public String minutes;
    public int done;
    public int id;

    public TimerModel(int done, int id, String minutes, String title) {
        this.done = done;
        this.id = id;
        this.minutes = minutes;
        this.title = title;
    }

    public TimerModel() {
    }

    public TimerModel(int id, String minutes, String title) {
        this.id = id;
        this.minutes = minutes;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
