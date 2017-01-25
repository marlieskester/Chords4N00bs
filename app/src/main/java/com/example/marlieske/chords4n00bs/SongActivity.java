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

import static android.R.attr.data;
import static com.example.marlieske.chords4n00bs.R.string.chord;

public class SongActivity extends AppCompatActivity {
    Song song;
    ArrayList<Lyrics> songContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Intent selectedSong = getIntent();
        Bundle data = selectedSong.getExtras();
        song = data.getParcelable("song");
        Log.d("songact", "x"+song.title);
        setInfo();
    }

    public void setInfo(){
        TextView Title = (TextView) findViewById(R.id.Songtitle);
        Title.setText(song.title);
        TextView Key = (TextView) findViewById(R.id.Key);
        // settext
        CheckBox diagram = (CheckBox) findViewById(R.id.Diagram);
        // onchecked
        RadioButton guitar = (RadioButton) findViewById(R.id.Ukulele);
        RadioButton Ukulele = (RadioButton) findViewById(R.id.Guitar);
        if (guitar.isChecked()){
            Ukulele.setChecked(false);
        }
        else {
            Ukulele.setChecked(true);
        }
        Log.d("songact", "setinfo");
        parser2 parser = new parser2();
        songContent = parser.parse(song);
        Log.d("duurt", "lang");
    }

//    public ArrayList transposeStepOne(){
//        ArrayList chords = new ArrayList();
//        for (int i = 0; i < songContent.size(); i++){
//            LyricsParser.Lyrics temp = songContent.get(i);
//            chords.add(temp.chord);
//        }
//        return chords;
//    }

//    public ArrayList<LyricsParser.Lyrics> transposeStepTwo(ArrayList chords){
//        for (int i = 0; i < songContent.size(); i++){
//            LyricsParser.Lyrics temp = songContent.get(i);
//            //temp.chord = chords.get(i);
//            chords.add(temp.chord);
//        }
//        return songContent;
//    }

    public void transposeStep1(String direction){
        for (int i = 0; i < songContent.size(); i++){
            Lyrics temp = songContent.get(i);
            for (int j = 0; j < temp.chord.size(); j++){
                String scaleChord = temp.chord.get(j);
                Log.d("transpose", scaleChord);
                if (direction.equals("up")){
                    scaleChord = transposeUP(scaleChord);
                }
                else {
                    scaleChord = transposeDOWN(scaleChord);
                }
                temp.chord.set(j, scaleChord);
                Log.d("transpose", scaleChord);
            }
        }
        transposeStep2();
    }

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
                chord = chord.replace("G", "G#");
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


    public void scaleUp(View view) {
        transposeStep1("up");
    }

    public void scaleDown(View view) {
        transposeStep1("down");
    }


    public void transposeStep2(){

    }

    public void chooseInstrument(){
        // def: guitar
        // als radio 1 checked, radio 2 neit checked
    }


    public void playSong(View view) {
        CheckBox diagram = (CheckBox) findViewById(R.id.Diagram);
        //intent naar songview
        Intent toplaySong = new Intent(this, PlayActivity.class);
        toplaySong.putExtra("content", songContent);
        toplaySong.putExtra("checked", diagram.isChecked());
        toplaySong.putExtra("song", song);
        toplaySong.putParcelableArrayListExtra("content2", songContent);
        startActivity(toplaySong);
    }

    public void saveSettings(View view) {
        DatabaseHelper helper = new DatabaseHelper(this);
        helper.create(song);
        Toast.makeText(this, "added to Songbook", Toast.LENGTH_SHORT).show();
        // naar songbook?
    }
}