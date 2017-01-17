package com.example.marlieske.chords4n00bs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class SongBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_book);
        DisplaySongList();
    }

    /** Connects Arralylist to adapter*/
    public void DisplaySongList(){
        DatabaseHelper helper = new DatabaseHelper(this);
        ArrayList<Song> songs = helper.read();
        ListView LVItems = (ListView) findViewById(R.id.booklist);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.result_layout, songs, "SongBook");
        Log.d("book", "x" + songs);
        LVItems.setAdapter(adapter);
    }

}
