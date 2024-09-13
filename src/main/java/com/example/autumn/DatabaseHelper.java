package com.example.autumn;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "usersDB.db";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "usersDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table players(id INTEGER primary key AUTOINCREMENT, name TEXT , age TEXT, appearances TEXT, goals TEXT, form TEXT, position TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    /*
    Insert into Tables
     */
    public Boolean insertData(String email, String password, String basket, String total){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean insertIntoPlayers(String name, String age, String appearances, String goals, String form, String position){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", (byte[]) null);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("appearances", appearances);
        contentValues.put("goals", goals);
        contentValues.put("form", form);
        contentValues.put("position", position);
        long result = MyDatabase.insert("products", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint("Range")
    public List<String> getAllNames(){


        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select name from players", null);

        int length = cursor.getCount();
        List<String> names = new ArrayList<>();

        if (cursor.moveToFirst()){
            for (int i = 0; i < length; i++) {
                names.add(cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
        }

        return names;
    }
    @SuppressLint("Range")
    public List<Player> getAllPlayers(){


        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from players", null);

        int length = cursor.getCount();
        List<Player> players = new ArrayList<>();

        if (cursor.moveToFirst()){
            for (int i = 0; i < length; i++) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String age = cursor.getString(cursor.getColumnIndex("age"));
                String appearances = cursor.getString(cursor.getColumnIndex("appearances"));
                String goals = cursor.getString(cursor.getColumnIndex("goals"));
                String form = cursor.getString(cursor.getColumnIndex("form"));
                String position = cursor.getString(cursor.getColumnIndex("position"));


                Player player = new Player(name, age, appearances, goals, form, position);
                players.add(player);

                cursor.moveToNext();
            }
        }

        return players;
    }


    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}