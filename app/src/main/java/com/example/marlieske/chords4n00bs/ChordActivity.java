package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/***
 * Created by Marlieske Doorn
 * This activity helps users find a way to play a chord if they don't know how to.
 * The activity gets an IMG-URI from the asynctask which was triggered in the main activity,
 * it is then extracted and run in another asynctask to download the image.
 */

public class ChordActivity extends AppCompatActivity {
    String result;
    static Chord chord;

    /**constructor**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_list);
        Intent toListOfChords = getIntent();
        result = toListOfChords.getStringExtra("result");
        getContent();
    }

    /**triggers JSONExtractor and DownloadImageTask to get to the image**/
    void getContent(){
        TextView chordTitle = (TextView) findViewById(R.id.chordName);
        chordTitle.setText(chord.name);
        new DownloadImageTask(this).execute(chord.imgurl);
    }

    /**connects image to imageView**/
    public void setImg(Bitmap input) {
        ImageView chordDiagram = (ImageView) findViewById(R.id.chord_diagram);
        chordDiagram.setBackgroundColor(Color.WHITE);
        chordDiagram.setImageBitmap(input);
    }
}
