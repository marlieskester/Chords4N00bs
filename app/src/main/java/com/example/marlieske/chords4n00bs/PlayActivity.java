package com.example.marlieske.chords4n00bs;

import android.animation.Animator;
import android.animation.AnimatorSet;
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
    int speed = 50000;

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
        animation(speed);
    }

    public void showSong(){
        Button fasterButton = (Button) findViewById(R.id.speed_up);
        Button slowerButton = (Button) findViewById(R.id.speed_down);
        fasterButton.bringToFront();
        slowerButton.bringToFront();
        TextView textView = (TextView) findViewById(R.id.playTitle);
        textView.setText(song.title);
        PlayListAdapter adapter = new PlayListAdapter(this, R.layout.lyrics, songContent2, diagram);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }

    public void faster(View view) {
        if (speed >= 300) {
            speed = speed - 300;
        }
        else if (speed >= 50) {
            speed = speed - 50;
        }
        animation(speed);
    }

    public void animation(int speed){
        final ListView listView = (ListView) findViewById(R.id.listview);
      //  listView.setTop(0);
        int amount = songContent2.size();
        listView.smoothScrollToPositionFromTop(amount, 0, speed);
    }


    public void slower(View view) {
        if (speed < 1000) {
            speed = speed + 100;
        } else if (speed  < 5000){
            speed = speed + 300;
        } else {
            speed = speed + 700;
        }
        animation(speed);
    }
}
