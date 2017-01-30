package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/***
 * Created by Marlieske Doorn
 * This activity helps users find a way to play a chord if they don't know how to.
 * The activity gets an IMG-URI from the asynctask which was triggered in the main activity,
 * it is then extraacted and run in another asynctask to download the image.
 */

//TODO: deze nog mooi maken, ukulele optie

public class ChordListActivity extends AppCompatActivity {
    String result;

    /**contructor**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_list);
        Intent toListOfChords = getIntent();
        result = toListOfChords.getStringExtra("result");
        getContent();
    }

    /**triggers JSONextractor and DownloadimageTask to get to the image**/
    void getContent(){
        JSONExtractor extractor = new JSONExtractor();
        Chord chord = extractor.getChord(result);
        TextView chordTitle = (TextView) findViewById(R.id.chordName);
        chordTitle.setText(chord.name);
        new DownloadImageTask().execute(chord.imgurl);
    }

    /**connects image to imageview**/
    public void setImg(Bitmap input) {
        ImageView chordDiagram = (ImageView) findViewById(R.id.ChordDiagram);
        chordDiagram.setImageBitmap(input);
    }
}
