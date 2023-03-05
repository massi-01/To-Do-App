package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MyAdapter extends ArrayAdapter<ToDoObject> {

    private final LayoutInflater inflater;
    private OnDeleteButtonClickListener clickListener = null;
    private OnTitleClickListener titleClickListener = null;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<ToDoObject> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    public void setItemClickListener(OnDeleteButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setTitleClickListener(OnTitleClickListener titleClickListener){
        this.titleClickListener = titleClickListener;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if (v == null) {
            v = inflater.inflate(R.layout.list_element, parent, false);
        }

        ToDoObject toDoObject = getItem(position);

        ImageButton deleteButton = v.findViewById(R.id.deleteButton);
        TextView titleTextView = v.findViewById(R.id.title);
        TextView dateTextView = v.findViewById(R.id.date);

        titleTextView.setText(toDoObject.getTitle());
        dateTextView.setText(toDoObject.getDate());

        deleteButton.setOnClickListener(view -> {
            if (this.clickListener == null) return;
            clickListener.onClick(toDoObject);
        });

        titleTextView.setOnClickListener(view -> {
            if (this.titleClickListener == null) return;
            try {
                titleClickListener.onClick(toDoObject.getTitle());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        return v;
    }
}
