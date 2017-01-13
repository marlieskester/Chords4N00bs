package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadSonglist(View view) {
        EditText ETKeyWord = (EditText) findViewById(R.id.songsearch);
        String Keyword = ETKeyWord.getText().toString();
        loadAsync(Keyword, "song");
    }

    public void loadArtistlist(View view) {
//        EditText ETKeyWord = (EditText) findViewById(R.id.artistsearch);
//        String Keyword = ETKeyWord.getText().toString();
//        loadAsync(Keyword, "artist");
        Intent temporary = new Intent(this, SongListActivity.class);
        startActivity(temporary);
    }

    public void loadChords(View view) {
//        EditText ETKeyWord = (EditText) findViewById(R.id.chordsearch);
//        String Keyword = ETKeyWord.getText().toString();
//        loadAsync(Keyword, "chord");
        Intent temp2 = new Intent(this, ChordListActivity.class);
        startActivity(temp2);
    }


    private void loadAsync(String Keyword, String origin){
        if (Keyword.equals("")) {
            Toast.makeText(this, "Please enter Keyword", Toast.LENGTH_SHORT).show();
        }
        else {
            AsyncTask thisAsyncTask = new SearchAsyncTask(this);
            thisAsyncTask.execute(Keyword, origin);
        }
    }

    public void loadSongBook(View view) {
        Intent toSongbookActivity = new Intent(this, SongBookActivity.class);
        startActivity(toSongbookActivity);
    }

}
