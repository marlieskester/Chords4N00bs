package com.example.marlieske.chords4n00bs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Marlieske Doorn
 * Activity displays list containing all songs saved by user.
 * The layout is familiar for the user and works the same as in the resultactivity.
 */

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
        } else {
            ListView LVItems = (ListView) findViewById(R.id.songbook_listview);
            ResultListAdapter adapter = new ResultListAdapter(this, R.layout.listview_result_entry, songs, "songbook");
            LVItems.setAdapter(adapter);
        }
    }
}
