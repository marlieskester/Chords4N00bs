package com.example.marlieske.chords4n00bs;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import static android.R.attr.animation;
import static com.example.marlieske.chords4n00bs.R.id.scrollView;

public class PlayActivity extends AppCompatActivity {
Song song;
    Boolean diagram;
    ArrayList<Lyrics> songContent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent playSong = getIntent();
        Bundle data = playSong.getExtras();
        songContent2 = data.getParcelableArrayList("content2");

        diagram = data.getBoolean("checked");
        song = data.getParcelable("song");
        showSong();
        setScreen();
    }

    void setScreen(){
        Button fasterButton = (Button) findViewById(R.id.speed_up);
        Button slowerButton = (Button) findViewById(R.id.speed_down);
        fasterButton.bringToFront();
        slowerButton.bringToFront();
    }

    public void showSong(){
        TextView textView = (TextView) findViewById(R.id.playTitle);
        textView.setText(song.title);
//        LyricsParser parser = new LyricsParser();
//        ArrayList<LyricsParser.Lyrics> songContent = parser.parse(song);
//        parser2 parser = new parser2();
//        ArrayList<Lyrics> songContent = parser.parse(song);
        PlayListAdapter adapter = new PlayListAdapter(this, R.layout.lyrics, songContent2, diagram);

        //ArrayAdapter adapter = new ArrayAdapter<Lyrics>(this, R.layout.songtext, playsong);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }

    void something(){
        ObjectAnimator.ofInt(scrollView, "scrollY", 250, 0).setDuration(800).start();
    }

    void PlanB(){
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.smoothScrollBy(5000, 0);
    }

    public void startanimation(View view) {
        Toast.makeText(this, "hij doet het", Toast.LENGTH_SHORT).show();
       // ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        ListView listView = (ListView) findViewById(R.id.listview);
        //listView.smoothScrollToPosition(amount);
        int amount = songContent2.size();
        while (listView.getLastVisiblePosition() != amount){
            ObjectAnimator.ofInt(listView, "scrollY", 60).setDuration(10).start();
        }
    }



}
