package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.attr.name;
import static com.example.marlieske.chords4n00bs.R.id.ChordDiagram;

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

  //  Chord getContent(){
//        Chord chord = null;
//        try {
//            JSONObject jsonwholething = null;
//            jsonwholething = new JSONObject(result);
//            JSONArray jresults = (JSONArray) jsonwholething.get("objects");
//            JSONObject result = jresults.getJSONObject(0);
//            String name = result.getString("name");
//            String imgurl = result.getString("image_url");
//            chord = new Chord(name, imgurl);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return chord;
   // }

    void setContent(){
        JSONExtractor extractor = new JSONExtractor();
        Chord chord = extractor.getChord(result);

        ImageView chordDiagram = (ImageView) findViewById(R.id.ChordDiagram);
        TextView chordTitle = (TextView) findViewById(R.id.chordName);

        chordTitle.setText(chord.name);
        new DownloadImageTask(chordDiagram).execute(chord.imgurl);
    }

}
