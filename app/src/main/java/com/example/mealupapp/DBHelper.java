package com.example.mealupapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table Userdetails(username TEXT primary key, " +
                "password INTEGER, age INTEGER)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qry = "drop Table if exists Userdetials";
        db.execSQL(qry);
    }

    public boolean insertUserData(String username, int password, int age){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        //will add the actual dob later.
        contentValues.put("age", age);

        long result = DB.insert("Userdetails", null, contentValues);

        //failed
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateUserData(String username, int password, int age) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("age", age);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?", new String[]{username});

        if (cursor.getCount() > 0) {

            long result = DB.update("Userdetails", contentValues, "username?", new String[]{username});

            //failed
            if (result == -1)
                return false;
            else
                return true;
        } else
            return false;
    }

    public boolean deleteUserData(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?", new String[]{username});

        if (cursor.getCount() > 0) {

            long result = DB.delete("Userdetails", "username?", new String[]{username});

            //failed
            if (result == -1)
                return false;
            else
                return true;
        } else
            return false;
    }

    public Cursor getData(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        password += "P#!@t";
        String hashed_pass = String.valueOf(password.hashCode());
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ? and password = ?", new String[]{username, hashed_pass});
        return cursor;
    }
}
