package com.example.todo;

public class ToDoObject {
    String title;
    String date;

    public ToDoObject(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
