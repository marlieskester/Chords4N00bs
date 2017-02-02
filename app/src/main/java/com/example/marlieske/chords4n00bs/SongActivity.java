package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Marlieske Doorn
 * Activity forms connection between songlist and play.
 * Offers transpose and save options.
 * NOTE:
 * To determine key, all chords must be conidered, exceptions must be found and a key can be extracted.
 * Or, 90% of the songs start out with a chord equal to the key, which I preferred to use in this case.
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
        setKey();
    }

    /**sets title and int firstChord**/
    public void setInfo(){
        TextView Title = (TextView) findViewById(R.id.Songtitle);
        Title.setText(song.title);
        Key = (TextView) findViewById(R.id.Key);
        while (songContent.get(firstChord).chord.isEmpty()){
            firstChord ++;
        }
    }

    /**adapts the textview to the current key**/
    void setKey(){
        String first = songContent.get(firstChord).chord.get(0);
        String keyText = "Key: " + first.substring(1, first.length() - 1);
        Key.setText(keyText);
    }

    /**extract all chords from lyrics, change pitch, put back**/
    public void transposeStep1(String direction){
        ScaleChanger changer = new ScaleChanger();
        for (int i = 0; i < songContent.size(); i++){
            Lyrics temp = songContent.get(i);
            for (int j = 0; j < temp.chord.size(); j++){
                String scaleChord = temp.chord.get(j);
                if (direction.equals("up")){
                    scaleChord = changer.transposeUP(scaleChord);
                } else {
                    scaleChord = changer.transposeDOWN(scaleChord);
                }
                temp.chord.set(j, scaleChord);
            }
        }
        setKey();
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