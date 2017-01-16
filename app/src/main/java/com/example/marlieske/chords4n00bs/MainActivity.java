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

//    public void loadSonglist(View view) {
//        EditText ETKeyWord = (EditText) findViewById(R.id.songsearch);
//        String Keyword = ETKeyWord.getText().toString();
//        loadAsync(Keyword, "song");
//}

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




    public void loadSongBook(View view) {
        Intent toSongbookActivity = new Intent(this, SongBookActivity.class);
        startActivity(toSongbookActivity);
    }

    public void searchAll(View view) {
        AsyncTask thisAsyncTask = new SearchAsyncTask(this);

        EditText ETSong = (EditText) findViewById(R.id.songsearch);
        String title = ETSong.getText().toString();
        EditText ETartist = (EditText) findViewById(R.id.artistsearch);
        String artist = ETartist.getText().toString();
        EditText ETChord = (EditText) findViewById(R.id.chordsearch);
        String chord = ETChord.getText().toString();

        if (!title.equals("")){
            thisAsyncTask.execute(title, "song");
        }
        else if (!artist.equals("")){
            thisAsyncTask.execute(artist, "artist");
        }
        else if (!chord.equals("")){
            thisAsyncTask.execute(chord, "chord");
        }
        else {
            Toast.makeText(this, "Please enter Keyword", Toast.LENGTH_SHORT).show();
        }
    }
}
