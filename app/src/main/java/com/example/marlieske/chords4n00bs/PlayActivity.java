package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marlieske Doorn
 * Activity shows a listview containing lyrics and chords. Offers option for automatic scrolldown.
 */


public class PlayActivity extends AppCompatActivity {
    Song song;
    Boolean diagram;
    ArrayList<Lyrics> songContent;
    int speed = 50000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent playSong = getIntent();
        Bundle data = playSong.getExtras();
        songContent = data.getParcelableArrayList("content");
        diagram = data.getBoolean("checked");
        song = data.getParcelable("song");
        showSong();
        animation(speed);
    }

    /**sets screen; buttons, title and list are alla adapted to current situation**/
    public void showSong(){
        Button fasterButton = (Button) findViewById(R.id.play_button_faster);
        Button slowerButton = (Button) findViewById(R.id.play_button_slower);
        fasterButton.bringToFront();
        slowerButton.bringToFront();
        TextView textView = (TextView) findViewById(R.id.playTitle);
        textView.setText(song.title);
        PlayListAdapter adapter = new PlayListAdapter(this, R.layout.listview_lyrcs_entry, songContent, diagram);
        ListView listview = (ListView) findViewById(R.id.play_listview);
        listview.setAdapter(adapter);
    }

    /**adapt scrollspeed**/
    public void faster(View view) {
        if (speed >= 10){
            speed = speed - speed / 10;
        }
        animation(speed);
    }

    public void slower(View view) {
        speed = speed + speed / 10;
        animation(speed);
    }

    /**autoscroll function**/
    public void animation(int speed){
        final ListView listView = (ListView) findViewById(R.id.play_listview);
        int amount = songContent.size();
        listView.smoothScrollToPositionFromTop(amount, 0, speed);
    }
}
