package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Marlieske on 10-1-2017.
 */
public class SearchAsyncTask extends AsyncTask<Object, Void, String> implements downloadImgInterface{
    private Context mContext;
    private MainActivity mActivity;
    private String origin;
    private onFinished mListener;
    private Bitmap image;
    private int positionInList;


    public interface AsyncResponse{
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;


    /** constructor */
    public SearchAsyncTask(Context activity,int positionInList, onFinished listener){
      //  this.mActivity = activity;
      //  this.mContext = this.mActivity.getApplicationContext();
        this.mContext = activity;
        this.mListener = listener;
        this.positionInList = positionInList;
    }

    public SearchAsyncTask(Context context){
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
//        Toast.makeText(mContext, "Loading...", Toast.LENGTH_SHORT).show();
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
            JSONExtractor extractor = new JSONExtractor();
                Chord chord = extractor.getChord(result);
                new DownloadImageTask(this).execute(chord.imgurl);
      //      mListener.processFinish(result);
        }
    }
    @Override
    public void returnImg(Bitmap input) {
        mListener.processFinish(input, positionInList);

    }

}
