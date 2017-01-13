package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.R.attr.data;

public class SongActivity extends AppCompatActivity {
    Song song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
      //  Intent selectedSong = getIntent();
      //  Bundle data = selectedSong.getExtras();
      //  song = data.getParcelable("song");
    }



    public String scaleDown(View view) {
        String chord = null;
        if (chord.contains("#")){
            chord.replace("#", "");
        }
        else {
            if (chord.contains("Ab")) {
                chord.replace("Ab", "G");
            } else if (chord.contains("Bb")){
                chord.replace("Bb", "A");
            } else if (chord.contains("Cb")) {
                chord.replace("C", "B");
            } else if (chord.contains("Db")) {
                chord.replace("Db", "C");
            } else if (chord.contains("Eb")) {
                chord.replace("Eb", "D");
            } else if (chord.contains("Fb")) {
                chord.replace("F", "E");
            } else if (chord.contains("Gb")) {
                chord.replace("Gb", "F");
            } else if (chord.contains("A")) {
                chord.replace("A", "G#");
            } else if (chord.contains("B")){
                chord.replace("B", "Bb");
            } else if (chord.contains("C")) {
                chord.replace("C", "B");
            } else if (chord.contains("D")) {
                chord.replace("D", "C#");
            } else if (chord.contains("E")) {
                chord.replace("E", "Eb");
            } else if (chord.contains("F")) {
                chord.replace("F", "E");
            } else if (chord.contains("G")) {
                chord.replace("G", "F#");
            }
        }
        return chord;
    }

    public void scaleUp(View view) {
        String chord = null;
        if (chord.contains("b")){
            chord.replace("b", "");
        } else {
            if (chord.contains("A#")) {
                chord.replace("A#", "B");
            } else if (chord.contains("B#")){
                chord.replace("B", "C");
            } else if (chord.contains("C#")) {
                chord.replace("C#", "D");
            } else if (chord.contains("D#")) {
                chord.replace("D#", "E");
            } else if (chord.contains("E#")) {
                chord.replace("E", "F");
            } else if (chord.contains("F#")) {
                chord.replace("F#", "G");
            } else if (chord.contains("G#")) {
                chord.replace("G#", "A");
            } else if (chord.contains("A")) {
                chord.replace("A", "Bb");
            } else if (chord.contains("B")){
                chord.replace("B", "C");
            } else if (chord.contains("C")) {
                chord.replace("C", "C#");
            } else if (chord.contains("D")) {
                chord.replace("D", "Eb");
            } else if (chord.contains("E")) {
                chord.replace("E", "F");
            } else if (chord.contains("F")) {
                chord.replace("F", "F#");
            } else if (chord.contains("G")) {
                chord.replace("G", "G#");
            }
        }

    }

    public void showDiagram(){
        //onchecked
    }

    public void chooseInstrument(){
        // def: guitar
        // als radio 1 checked, radio 2 neit checked
    }

    public void viewSong(View view) {
        //intent naar songview
        Intent playSong = new Intent(this, PlayActivity.class);
        startActivity(playSong);

    }

    public void saveSettings(View view) {
        // save to SQL
        // show toast
        // naar songbook?
    }
}
