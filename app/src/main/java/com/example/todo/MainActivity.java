package com.example.todo;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    public List<ToDoObject> toDoList;
    public MyAdapter myAdapter;
    public DatabaseHelper databaseHelper;
    public SQLiteDatabase sqLiteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDoList = new ArrayList<>();

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TODO", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));

                ToDoObject toDoObject = new ToDoObject(id, title, date);
                toDoList.add(toDoObject);
            } while (cursor.moveToNext());
        }

        cursor.close();
        listView = findViewById(R.id.listView);

        myAdapter = new MyAdapter(this, R.layout.list_element, toDoList);

        myAdapter.setItemClickListener(todo -> {
            pressedDeleteButton(todo);
            myAdapter.notifyDataSetChanged();

        });

        myAdapter.setTitleClickListener(this::startSearch);

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

            String title = titleEditText.getText().toString();
            String date = datePicker.getDayOfMonth() + "/"
                    + (datePicker.getMonth() + 1) + "/"
                    + datePicker.getYear();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(*) FROM TODO", null);


            toDoList.add(new ToDoObject(cursor.getCount(), title, date));
            sqLiteDatabase.execSQL("INSERT INTO TODO(title, date) VALUES ('" + title + "', '" + date + "');");
            myAdapter.notifyDataSetChanged();

            cursor.close();
        });

        builder.setNegativeButton("Cancel", (dialog, id) -> {

        });

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void pressedDeleteButton(ToDoObject todo) {
        sqLiteDatabase.execSQL("DELETE FROM TODO WHERE id = '" + todo.getId() + "';");
        toDoList.remove(todo);
    }

    public void startSearch(String title) {
        Intent i = new Intent(Intent.ACTION_WEB_SEARCH)
                .putExtra(
                    SearchManager.QUERY,
                    title
                );
        startActivity(i);
    }
}