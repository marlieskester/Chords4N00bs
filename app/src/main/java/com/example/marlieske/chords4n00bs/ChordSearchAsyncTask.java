package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Marlieske on 2-2-2017.
 * Runs on background; searches for information on the web and returns output.
 * The reason to create two versions is becuase the postexecute became very messy with a lot of nested if's.
 */

class ChordSearchAsyncTask extends AsyncTask<Object, Void, String> {

    private Context mContext;

    /** constructor */
    ChordSearchAsyncTask(Context context){
        this.mContext = context;
    }

    /** executes HTTPrequest, returns URL */
    @Override
    protected String doInBackground(Object... params) {
        String origin = (String) params[1];
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
