package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Marlieske Doorn
 * Activity shows a list with songs returned from the API request.
 */

public class SongListActivity extends AppCompatActivity {
    String result;
    static ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        Intent toListOfSongs = getIntent();
        result = toListOfSongs.getStringExtra("result");
        DisplaySongList();
    }

    /** Connects Arralylist to adapter*/
    public void DisplaySongList(){
        ListView LVItems = (ListView) findViewById(R.id.songlist_listview);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.listview_result_entry, songs, "ListofSongs");
        LVItems.setAdapter(adapter);
    }

}
