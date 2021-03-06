package com.example.marlieske.chords4n00bs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by Marlieske on 16-1-2017.
 * Class contains all SQL code used for songbook maintanance: read, write, delete.
 */

class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "myDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "songBook";
    private String title_id = "title";
    private String artist_id = "artist";
    private String content_id = "content";

    /**constructor**/
    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " + title_id + " TEXT, " + artist_id + " TEXT, " + content_id + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    /**add song to SQL**/
    void create(Song song) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(title_id, song.title);
        values.put(artist_id, song.artist);
        values.put(content_id, song.content);
        db.insert(TABLE, null, values);
    }

    /**delete from SQL by searching title**/
     void delete(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE + " WHERE " + title_id + "='" + title + "';");
    }

    /**read from SQL**/
    ArrayList<Song> read() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id, " + title_id + ", " + artist_id + ", " + content_id + " FROM " + TABLE;
        ArrayList<Song> myList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(title_id));
                String artist = cursor.getString(cursor.getColumnIndex(artist_id));
                String content = cursor.getString(cursor.getColumnIndex(content_id));
                Song newSong = new Song(title, artist, content);
                myList.add(newSong);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return myList;
    }

}
