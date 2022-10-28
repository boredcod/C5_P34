package com.example.c5_p34;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "todolistDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_TODO = "todo";
    private static final String ID = "id";
    private static final String TODO_COL = "event";
    private static final String DUE_COL ="duedate";

    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "create table " + TABLE_TODO + "( " + ID
                + " integer primary key autoincrement, " +
                TODO_COL + " TEXT,"
                + DUE_COL + " TEXT)";

        sqLiteDatabase.execSQL( sqlCreate );
    }
    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> GetAllInHash(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> todoList = new ArrayList<>();
        String query = "SELECT id, event, duedate FROM "+ TABLE_TODO;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> todo = new HashMap<>();
            String sid = String.valueOf(cursor.getInt(cursor.getColumnIndex(ID)));
            todo.put("id", sid);
            todo.put("event",cursor.getString(cursor.getColumnIndex(TODO_COL)));
            todo.put("duedate",cursor.getString(cursor.getColumnIndex(DUE_COL)));
            todoList.add(todo);
        }
        return todoList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_TODO);
        onCreate(sqLiteDatabase);
    }

    public void insert ( Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_TODO;
        sqlInsert += " values( null, '" + todo.getEvent();
        sqlInsert += "', '" + todo.getDuedate() + "' )";
        System.out.println(sqlInsert);
        db.execSQL( sqlInsert );
        db.close();
    }
    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_TODO;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }
}
