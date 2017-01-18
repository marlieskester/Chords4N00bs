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

    public void loadSongBook(View view) {
        Intent toSongbookActivity = new Intent(this, SongBookActivity.class);
        startActivity(toSongbookActivity);
    }

    public void searchAll(View view) {
        AsyncTask thisAsyncTask = new SearchAsyncTask(this);

        EditText ETSong = (EditText) findViewById(R.id.songsearch);
        String title = ETSong.getText().toString();
        EditText ETChord = (EditText) findViewById(R.id.chordsearch);
        String chord = ETChord.getText().toString();

        if (!title.equals("")){
            if (title.contains(" ")) {
                title = title.replace(" ", "+");
            }
            thisAsyncTask.execute(title, "song");
        }
        else if (!chord.equals("")){
            if (chord.contains(" ")) {
                chord = chord.replace(" ", "+");
            }
            thisAsyncTask.execute(chord, "chord");
        }
        else {
            Toast.makeText(this, "Please enter Keyword", Toast.LENGTH_SHORT).show();
        }
    }
}
