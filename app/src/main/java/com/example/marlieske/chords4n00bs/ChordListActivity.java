package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ChordListActivity extends AppCompatActivity {
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_list);
        Intent toListOfChords = getIntent();
        result = toListOfChords.getStringExtra("result");
        setContent();
    }

    void setContent(){
        ImageView ChordDiagram = (ImageView) findViewById(R.id.ChordDiagram);
    //    ChordDiagram.setImageURI();
    }
}
