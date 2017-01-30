package com.example.marlieske.chords4n00bs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Marlieske on 25-1-2017.
 * source: stackoverflow, "Android Developer"
 *
 * Class downloads image using an asynctask and then calls setIMG to connect to imageview.
 */

class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{

    /**constructor**/
    DownloadImageTask(){
        }

    /**open connection and download image**/
    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap myDiagram = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            myDiagram = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            Log.d("chordlist", "catch");
            e.printStackTrace();
        }
        return myDiagram;
    }

    /**call function that connects image to imageview**/
    protected void onPostExecute(Bitmap result){
        ChordListActivity activity = new ChordListActivity();
        activity.setImg(result);
    }
}

