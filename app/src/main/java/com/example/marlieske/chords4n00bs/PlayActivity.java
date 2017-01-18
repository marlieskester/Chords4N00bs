package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
Song song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent playSong = getIntent();
        Bundle data = playSong.getExtras();
        song = data.getParcelable("content");
        setInfo();
        parser();

    }

    public void setInfo(){
        TextView textView = (TextView) findViewById(R.id.playTitle);
        textView.setText(song.title);
//        TextView textView = (TextView) findViewById(R.id.playTitle);
//        textView.setText(song.content);
//        textView.setMovementMethod(new ScrollingMovementMethod());

//        // get our html content
//        String htmlAsString = song.content;
//        Spanned htmlAsApanned = Html.fromHtml(htmlAsString);
//
//        TextView textView = (TextView) findViewById(R.id.chordsLyrics);
//        textView.setText(htmlAsApanned);
//        textView.setMovementMethod(new ScrollingMovementMethod());
//
////        WebView webView = (WebView) findViewById(R.id.webview);
////        webView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);


    }

    public void parser(){
       // TextView lyrics = (TextView) findViewById(R.id.lyricsLyrics);
        BufferedReader reader = new BufferedReader(new StringReader(song.content));
        ArrayList<Lyrics> playsong = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null){
                Lyrics newLyrics = new Lyrics();
                newLyrics.songtext = line;
                newLyrics.chord = new ArrayList<>();

                while (line.contains("[")){
                    int startChord = line.indexOf("[") + 1;
                    int endChord = line.indexOf("]");
                    String chord = line.substring(startChord, endChord);
                    newLyrics.chord.add(chord);
                    line = line.substring(endChord + 1);
                }

                line = reader.readLine();
                playsong.add(newLyrics);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlayListAdapter adapter = new PlayListAdapter(this, R.layout.lyrics, playsong);

        //ArrayAdapter adapter = new ArrayAdapter<Lyrics>(this, R.layout.songtext, playsong);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        ListViewAutoScrollHelper scrollHelper = new ListViewAutoScrollHelper(listview);
        scrollHelper.setEnabled(true);
    }

    class Lyrics {
        String songtext;
        ArrayList<String> chord;
//        String DiagramLink;
    }
}
