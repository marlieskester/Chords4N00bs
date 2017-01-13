package com.example.marlieske.chords4n00bs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SongBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_book);
    }

    /** Connects Arralylist to adapter*/
//    public void DisplaySongList(){
//        //read from SQL
//        ArrayList<Song> songs = SQLsongs;
//        ListView LVItems = (ListView) findViewById(R.id.listofstuff);
//        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.result_layout, songs, "ListofSongs");
//        LVItems.setAdapter(adapter);
//    }
}
