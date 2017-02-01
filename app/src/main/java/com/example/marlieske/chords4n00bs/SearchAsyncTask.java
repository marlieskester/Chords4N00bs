package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
        return HTTPRequestHelper.executeRequest(params[0], origin);
    }

    /** displays message to indicate start search */
    @Override
    protected void onPreExecute() {
        Toast.makeText(mContext, "Loading...", Toast.LENGTH_SHORT).show();
    }

    /** passes result to next activity depending on origin **/
    @Override
    public void onPostExecute(String result) {
        Log.d("async", result);
        JSONExtractor extractor = new JSONExtractor();
        if (result.equals("")) {
            Toast.makeText(mContext,"Sorry, nothing found" + result, Toast.LENGTH_SHORT).show();
        } else if (origin.equals("song")) {
            ArrayList<Song> songs = extractor.getSongs(result);
            Intent toListOfSongs = new Intent(mContext, SongListActivity.class);
            toListOfSongs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SongListActivity.tmp = result;
            SongListActivity.songs = songs;
            mContext.startActivity(toListOfSongs);
        } else if (origin.equals("chord")){
            Chord chord = extractor.getChord(result);
            if (chord == null) {
                Toast.makeText(mContext, "Sorry, nothing found", Toast.LENGTH_SHORT).show();
            } else {
                Intent toListOfChords = new Intent(mContext, ChordListActivity.class);
                toListOfChords.putExtra("result", result);
                ChordListActivity.chord = chord;
                toListOfChords.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(toListOfChords);
            }
        }
    }

}
