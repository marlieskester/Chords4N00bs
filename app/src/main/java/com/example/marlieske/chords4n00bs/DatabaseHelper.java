package com.example.marlieske.chords4n00bs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marlieske on 16-1-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "myDB.db";
    private static final String _ID = "_id";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "songBook";
    String title_id = "title";
    String artist_id = "artist";
  //  String key_id = "key";
    String content_id = "content";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " + title_id + " TEXT, " + artist_id + " TEXT " /**+ key_id + " TEXT " **/+ content_id + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void create(Song song) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(title_id, song.title);
        values.put(artist_id, song.artist);
    //    values.put(key_id, ??);
        values.put(content_id, song.content);
        Log.d("helper", values.toString());
        db.insert(TABLE, null, values);
    }

//    public ArrayList<HashMap<String, String>> read() {
//        SQLiteDatabase db = getReadableDatabase();
//        String query = "SELECT _id, " + title_id + ", " + artist_id + ", " + content_id + " FROM " + TABLE;
//        ArrayList<HashMap<String, String >> myList = new ArrayList<>();
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> newSong = new HashMap<>();
//                newSong.put("id", cursor.getString(cursor.getColumnIndex(_ID)));
//                newSong.put("title", cursor.getString(cursor.getColumnIndex(title_id)));
//                newSong.put("artist", cursor.getString(cursor.getColumnIndex(artist_id)));
//            //    newSong.put("key", cursor.getString(cursor.getColumnIndex(key_id)));
//                newSong.put("content", cursor.getString(cursor.getColumnIndex(content_id)));
//                myList.add(newSong);
//            }
//            while (cursor.moveToNext());
//
//        }
//        cursor.close();
//        db.close();
//        Log.d("DBRead", myList.toString());
//        return myList;
//    }

    public void delete(String _ID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, _ID, null);
    }

    public ArrayList<Song> read() {
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
        Log.d("DBRead", myList.toString());
        return myList;
    }

}
