package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
Song song;
    Boolean diagram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent playSong = getIntent();
        Bundle data = playSong.getExtras();
        song = data.getParcelable("content");
        diagram = data.getBoolean("checked");;
        showSong();
    }



    public void showSong(){
        TextView textView = (TextView) findViewById(R.id.playTitle);
        textView.setText(song.title);
        LyricsParser parser = new LyricsParser();
        ArrayList<LyricsParser.Lyrics> songContent = parser.parse(song);
        PlayListAdapter adapter = new PlayListAdapter(this, R.layout.lyrics, songContent, diagram);

        //ArrayAdapter adapter = new ArrayAdapter<Lyrics>(this, R.layout.songtext, playsong);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }
}
