package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("list", "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        Intent toListOfSongs = getIntent();
        result = toListOfSongs.getStringExtra("result");
       // DisplaySongList();
    }

    /** Connects Arralylist to adapter*/
    public void DisplaySongList(){
        JSONExtractor ex = new JSONExtractor(result);
        ArrayList<Song> songs = ex.getSongs();
        Log.d("list", "extractor");
        ListView LVItems = (ListView) findViewById(R.id.listofstuff);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.result_layout, songs, "ListofSongs");
        LVItems.setAdapter(adapter);
    }


    public void showSong(View view) {
        Intent toSongView = new Intent(this, SongActivity.class);
        startActivity(toSongView);
    }
}
