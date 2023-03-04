package com.example.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    public List<ToDoObject> toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        toDoList = new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_element, toDoList);
    }

    public void pressedAddButton(View view) {
        //TODO
        new AlertDialog.Builder(getApplicationContext()).setTitle("Add new");
    }
}