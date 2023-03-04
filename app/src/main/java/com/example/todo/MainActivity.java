package com.example.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    public List<ToDoObject> toDoList;
    public MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        toDoList = new ArrayList<>();

        myAdapter = new MyAdapter(this, R.layout.list_element, toDoList);

        listView.setAdapter(myAdapter);
    }

    public void pressedAddButton(View view) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialogView);


        builder.setPositiveButton("OK", (dialog, id) -> {
            EditText titleEditText = dialogView.findViewById(R.id.task_edit_text);
            DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
            String date = datePicker.getDayOfMonth() + "/"
                    + (datePicker.getMonth() + 1) + "/"
                    + datePicker.getYear();

            toDoList.add(new ToDoObject(titleEditText.getText().toString(), date));
            myAdapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancel", (dialog, id) -> {

        });

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();


    }
}