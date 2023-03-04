package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class MyAdapter extends ArrayAdapter<ToDoObject> {

    private final LayoutInflater inflater;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<ToDoObject> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if(v ==null){
            v = inflater.inflate(R.layout.list_element, parent, false);
        }
        return super.getView(position, v, parent);
    }
}
