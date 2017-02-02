package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Marlieske on 10-1-2017.
 * Runs on background; searches for information on the web and returns output.
 */
class SearchAsyncTask extends AsyncTask<Object, Void, String> {
    private Context mContext;
    private String origin;

    /** constructor */
    SearchAsyncTask(Context context){
        this.mContext = context;
    }

    /** executes HTTPrequest, returns URL */
    @Override
    protected String doInBackground(Object... params) {
        origin = (String) params[1];
        return HTTPRequestHelper.openConnection(params[0], origin);
    }

    /** displays message to indicate start search */
    @Override
    protected void onPreExecute() {
        Toast.makeText(mContext, "Loading...", Toast.LENGTH_SHORT).show();
    }

    /** passes result to next activity depending on origin **/
    @Override
    public void onPostExecute(String result) {
        if (origin.equals("song")) {
            JSONExtractor extractor = new JSONExtractor();
            ArrayList<Song> songs = extractor.getSongs(result);
            if (songs.size() == 0) {
                Toast.makeText(mContext, "Sorry, nothing found", Toast.LENGTH_SHORT).show();
            } else {
                Intent toListOfSongs = new Intent(mContext, SongListActivity.class);
                toListOfSongs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SongListActivity.songs = songs;
                mContext.startActivity(toListOfSongs);
            }
        } else {
            JSONExtractor extractor = new JSONExtractor();
            Chord chord = extractor.getChord(result);
            if (chord == null) {
                Toast.makeText(mContext, "Sorry, nothing found", Toast.LENGTH_SHORT).show();
            } else {
                Intent toListOfChords = new Intent(mContext, ChordActivity.class);
                ChordActivity.chord = chord;
                toListOfChords.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(toListOfChords);
            }
        }
    }
}
