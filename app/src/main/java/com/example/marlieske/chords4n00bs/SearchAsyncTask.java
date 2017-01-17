package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Marlieske on 10-1-2017.
 */
public class SearchAsyncTask extends AsyncTask<Object, Void, String> {
    private Context mContext;
    private MainActivity mActivity;
    private String origin;

    /** constructor */
    public SearchAsyncTask(MainActivity activity){
        this.mActivity = activity;
        this.mContext = this.mActivity.getApplicationContext();
    }

    /** executes HTTPrequest, returns URL */
    @Override
    protected String doInBackground(Object... params) {

        Log.d("doinbackground", "enter");
        origin = (String) params[1];
        return HTTPRequestHelper.executeRequest(params[0], origin);
    }

    /** displays message to indicate start search */
    @Override
    protected void onPreExecute() {
        Toast.makeText(mContext, "Loading...", Toast.LENGTH_SHORT).show();
    }

    /** passes result to next activity */
    @Override
    public void onPostExecute(String result) {
        Log.d("onpostexecute", origin);
        if (result.equals("")) {
            Toast.makeText(mContext,"Sorry, nothing found" + result, Toast.LENGTH_SHORT).show();
        } else if (origin.equals("song")) {
            Log.d("async", "song");
            Intent toListOfSongs = new Intent(mContext, SongListActivity.class);
            toListOfSongs.putExtra("result", result);
            toListOfSongs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("async", "song2");
            mContext.startActivity(toListOfSongs);
        } else { //chord
            Log.d("async", "else");
            Intent toListOfChords = new Intent(mContext, ChordListActivity.class);
            toListOfChords.putExtra("result", result);
            toListOfChords.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(toListOfChords);
        }
    }

}
