package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Marlieske Doorn
 * Activity allows user to search for chords and songs or to go to collection of saved songs.
 * In the chord search no # are accepted, because the API cannot handle these.
 * The other option is to manually handle sharps by changing c# to Db programmatically,
 * which would result in another function like scaleUP but then not even very useful.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**go to collection of saved songs**/
    public void loadSongBook(View view) {
        Intent toSongbookActivity = new Intent(this, SongBookActivity.class);
        startActivity(toSongbookActivity);
    }

    /**loads result on keyword search**/
    public void searchAll(View view) {
        EditText ETSong = (EditText) findViewById(R.id.main_ET_songsearch);
        String title = ETSong.getText().toString();
        EditText ETChord = (EditText) findViewById(R.id.main_ET_chordsearch);
        String chord = ETChord.getText().toString();

        SearchAsyncTask thisAsyncTask = new SearchAsyncTask(this);

        if (!title.equals("")){
            if (title.contains(" ")) {
                title = title.replace(" ", "+");
            }
            thisAsyncTask.execute(title, "song");
        } else if (!chord.equals("")){
            String withCap = chord.substring(0, 1).toUpperCase() + chord.substring(1);
            thisAsyncTask.execute(withCap, "chord");
        } else {
            Toast.makeText(this, "Please enter Keyword", Toast.LENGTH_SHORT).show();
        }
    }
}
