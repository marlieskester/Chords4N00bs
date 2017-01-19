package com.example.marlieske.chords4n00bs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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
        if (songs.isEmpty()){
            Toast.makeText(this, "You have no songs yet", Toast.LENGTH_SHORT).show();
        }
        else {
            ListView LVItems = (ListView) findViewById(R.id.booklist);
            ResultListAdapter adapter = new ResultListAdapter(this, R.layout.result_layout, songs, "songbook");
            LVItems.setAdapter(adapter);
        }
    }

}
