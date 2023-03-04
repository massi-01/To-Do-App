package com.example.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String TABLE_NAME = "TODO";
    private final static int DATABASE_VERSION = 1;
    private final static String CREATE_TABLE_QUERY = "CREATE TABLE TODO " +
        "                                           (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "title TEXT NOT NULL," +
                                                    "date TEXT NOT NULL) ";

    DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
