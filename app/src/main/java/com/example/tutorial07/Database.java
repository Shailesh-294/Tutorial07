package com.example.tutorial07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "person";
    public static final String COL_1 = "Id";
    public static final String Col_2 = "Username";
    public static final String Col_3 = "Password";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (Id INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,username);
        contentValues.put(Col_3,password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean verifyUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from person where USERNAME = ?",new String[] {username});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean verifylogin(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from person where USERNAME = ? and PASSWORD = ?",new String[] {username,password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.query(TABLE_NAME,null,null,null,null,null,null);
        return result;
    }
}
