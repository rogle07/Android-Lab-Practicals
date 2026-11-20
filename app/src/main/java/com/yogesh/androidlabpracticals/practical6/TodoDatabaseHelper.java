package com.yogesh.androidlabpracticals.practical6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TodoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todo_yogesh.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASKS = "tasks";
    public static final String COL_ID = "_id";
    public static final String COL_TASK = "task";
    public static final String COL_DONE = "is_done";

    public TodoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_TASKS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TASK + " TEXT NOT NULL, " +
                COL_DONE + " INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    public long insertTask(String task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TASK, task);
        cv.put(COL_DONE, 0);
        return db.insert(TABLE_TASKS, null, cv);
    }

    public List<TodoItem> getAllTasks() {
        List<TodoItem> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_TASKS, null, null, null, null, null, COL_ID + " DESC");
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndexOrThrow(COL_ID));
                String task = c.getString(c.getColumnIndexOrThrow(COL_TASK));
                boolean done = c.getInt(c.getColumnIndexOrThrow(COL_DONE)) == 1;
                list.add(new TodoItem(id, task, done));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public int updateTask(int id, String newTask) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TASK, newTask);
        return db.update(TABLE_TASKS, cv, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public int toggleDone(int id, boolean done) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_DONE, done ? 1 : 0);
        return db.update(TABLE_TASKS, cv, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public int deleteTask(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_TASKS, COL_ID + "=?", new String[]{String.valueOf(id)});
    }
}
