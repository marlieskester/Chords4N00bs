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
    public SearchAsyncTask(Context activity){
      //  this.mActivity = activity;
      //  this.mContext = this.mActivity.getApplicationContext();
        this.mContext = activity;
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

    /** passes result to next activity */
    @Override
    public void onPostExecute(String result) {
        Log.d("onpostexecute", origin);
        if (result.equals("")) {
            Toast.makeText(mContext,"Sorry, nothing found" + result, Toast.LENGTH_SHORT).show();
        } else if (origin.equals("song")) {
            Intent toListOfSongs = new Intent(mContext, SongListActivity.class);
        //    toListOfSongs.putExtra("result", result);
            toListOfSongs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SongListActivity.tmp = result;
            mContext.startActivity(toListOfSongs);
        } else if (origin.equals("chord")){ //chord
            Intent toListOfChords = new Intent(mContext, ChordListActivity.class);
            toListOfChords.putExtra("result", result);
            toListOfChords.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(toListOfChords);
        } else {

        }
    }

}
