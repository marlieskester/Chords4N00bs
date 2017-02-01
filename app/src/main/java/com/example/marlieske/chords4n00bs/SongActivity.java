package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Marlieske Doorn
 * Activity forms connection between songlist and play.
 * Offers transpose and save options.
 */

public class SongActivity extends AppCompatActivity {
    Song song;
    ArrayList<Lyrics> songContent;
    int firstChord = 0;
    TextView Key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Intent selectedSong = getIntent();
        Bundle data = selectedSong.getExtras();
        song = data.getParcelable("song");
        Parser parser = new Parser();
        songContent = parser.parse(song);
        setInfo();
    }

    public void setInfo(){
        TextView Title = (TextView) findViewById(R.id.Songtitle);
        Title.setText(song.title);
        Key = (TextView) findViewById(R.id.Key);
        while (songContent.get(firstChord).chord.isEmpty()){
            firstChord ++;
        }
        Key.setText(songContent.get(firstChord).chord.get(0));
       // Key.setText(songContent.get(0).chord.get(0));
//        RadioButton guitar = (RadioButton) findViewById(R.id.song_RB_Ukulele);
//        RadioButton Ukulele = (RadioButton) findViewById(R.id.song_RB_Guitar);
//
    }

    /**extract all chords from lyrics, change pitch, put back**/
    public void transposeStep1(String direction){
        //Transpose transpose = new Transpose();
        for (int i = 0; i < songContent.size(); i++){
            Lyrics temp = songContent.get(i);
            for (int j = 0; j < temp.chord.size(); j++){
                String scaleChord = temp.chord.get(j);
                if (direction.equals("up")){
                    scaleChord = transposeUP(scaleChord);
                    //scaleChord = transpose.ScaleUp(scaleChord);
                } else {
                    //scaleChord = transpose.ScaleDown(scaleChord);
                    scaleChord = transposeDOWN(scaleChord);
                }
                temp.chord.set(j, scaleChord);
            }
        }
        Key.setText(songContent.get(firstChord).chord.get(0));
    }

    /**all chords +.5**/
    //TODO als er tijd is: veranderen naar ascii
    public String transposeUP(String chord){
        if (chord.contains("b")){
            chord = chord.replace("b", "");
        } else {
            if (chord.contains("A#")) {
                chord = chord.replace("A#", "B");
            } else if (chord.contains("B#")){
                chord = chord.replace("B", "C");
            } else if (chord.contains("C#")) {
                chord = chord.replace("C#", "D");
            } else if (chord.contains("D#")) {
                chord = chord.replace("D#", "E");
            } else if (chord.contains("E#")) {
                chord = chord.replace("E", "F");
            } else if (chord.contains("F#")) {
                chord = chord.replace("F#", "G");
            } else if (chord.contains("G#")) {
                chord = chord.replace("G#", "A");
            } else if (chord.contains("A")) {
                chord = chord.replace("A", "Bb");
            } else if (chord.contains("B")){
                chord = chord.replace("B", "C");
            } else if (chord.contains("C")) {
                chord = chord.replace("C", "C#");
            } else if (chord.contains("D")) {
                chord = chord.replace("D", "Eb");
            } else if (chord.contains("E")) {
                chord = chord.replace("E", "F");
            } else if (chord.contains("F")) {
                chord = chord.replace("F", "F#");
            } else if (chord.contains("G")) {
                chord = chord.replace("G", "Ab");
            }
        }
        return chord;
    }

    public String transposeDOWN(String chord){
        if (chord.contains("#")) {
            chord = chord.replace("#", "");
        } else {
            if (chord.contains("Ab")) {
                chord = chord.replace("Ab", "G");
            } else if (chord.contains("Bb")) {
                chord = chord.replace("Bb", "A");
            } else if (chord.contains("Cb")) {
                chord = chord.replace("C", "B");
            } else if (chord.contains("Db")) {
                chord = chord.replace("Db", "C");
            } else if (chord.contains("Eb")) {
                chord = chord.replace("Eb", "D");
            } else if (chord.contains("Fb")) {
                chord = chord.replace("F", "E");
            } else if (chord.contains("Gb")) {
                chord = chord.replace("Gb", "F");
            } else if (chord.contains("A")) {
                chord = chord.replace("A", "G#");
            } else if (chord.contains("B")) {
                chord = chord.replace("B", "Bb");
            } else if (chord.contains("C")) {
                chord = chord.replace("C", "B");
            } else if (chord.contains("D")) {
                chord = chord.replace("D", "C#");
            } else if (chord.contains("E")) {
                chord = chord.replace("E", "Eb");
            } else if (chord.contains("F")) {
                chord = chord.replace("F", "E");
            } else if (chord.contains("G")) {
                chord = chord.replace("G", "F#");
            }
        }
        return chord;
    }

    /**attach function to button**/
    public void scaleUp(View view) {
        transposeStep1("up");
    }

    public void scaleDown(View view) {
        transposeStep1("down");
    }

    /**insert chords in copy of lyrics**/
    public ArrayList<Lyrics> transposeStep2(ArrayList<Lyrics> oldSong) {
        ArrayList<Lyrics> newSong = new ArrayList<>();

        for (int i = 0; i < oldSong.size(); i++) {
            Lyrics oldLyrics = oldSong.get(i);
            Lyrics newLyrics = new Lyrics(null, new ArrayList());
            int chordcount = 0;

            newLyrics.songtext = oldLyrics.songtext;
            newLyrics.chord = oldLyrics.chord;

            while (newLyrics.songtext.contains("_")) {
                newLyrics.songtext = newLyrics.songtext.replaceFirst("_", newLyrics.chord.get(chordcount));
                chordcount = chordcount + 1;
            }
            newSong.add(newLyrics);
        }
        return newSong;
    }

    /**collect information and send to next activity**/
    public void playSong(View view) {
        ArrayList<Lyrics> finalSong = transposeStep2(songContent);
        CheckBox diagram = (CheckBox) findViewById(R.id.song_CB_Diagram);
        Intent toPlaySong = new Intent(this, PlayActivity.class);
        toPlaySong.putExtra("checked", diagram.isChecked());
        toPlaySong.putExtra("song", song);
        toPlaySong.putParcelableArrayListExtra("content", finalSong);
        startActivity(toPlaySong);
    }

    /**save song to SQL**/
    public void saveSettings(View view) {
        DatabaseHelper helper = new DatabaseHelper(this);
        helper.create(song);
        Toast.makeText(this, "added to Songbook", Toast.LENGTH_SHORT).show();
    }
}