package com.example.todo;

public class ToDoObject {
    int id;
    String title;
    String date;

    public ToDoObject(int id,String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
