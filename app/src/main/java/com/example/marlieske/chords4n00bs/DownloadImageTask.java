package com.example.marlieske.chords4n00bs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Marlieske on 25-1-2017.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
    /**source: stackoverflow, "Android Developer"**/
        downloadImgInterface mListener;

        public DownloadImageTask(downloadImgInterface listener){
            this.mListener = listener;
        }

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

        protected void onPostExecute(Bitmap result){
            mListener.returnImg(result);
        }
    }

