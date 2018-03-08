package com.example.albin.forgetmenot.database;

/**
 * Created by albin on 12/11/16.
 */

public class ReminderModel {
    public int id;
    public String title;
    public String content;
    public String date;
    public String time;
    public int done;


    public ReminderModel() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReminderModel(String content, String date, int done, int id, String time, String title) {

        this.content = content;
        this.date = date;
        this.done = done;
        this.id = id;
        this.time = time;
        this.title = title;
    }
}
