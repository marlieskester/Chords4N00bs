package com.example.marlieske.chords4n00bs;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Marlieske on 10-1-2017.
 */

public class JSONExtractor {
    private String mResults;

    /**
     * constructor
     */
    public JSONExtractor(String results) {
        this.mResults = results;
    }

    /**
     * extracts info from JSON
     */
    public ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        JSONArray jsonsongs = new JSONArray();
        Log.d("extractor", "getsongs");
        try {
            JSONObject jsonwholething = new JSONObject(mResults);
            JSONArray jresults = (JSONArray) jsonwholething.get("objects");
            Log.d("extractor", "try");
            for (int i = 0; i < jsonsongs.length(); i++) {
                // for all songs, extract info from JSONArray, put in one song, add song to arraylist.
                try {
                    JSONObject result = jresults.getJSONObject(i);
                    JSONObject jsonSong = (JSONObject) result.get("authors");
                    String title = result.getString("title");
                    String artist = jsonSong.getString("name");
                    String content = result.getString("body"); /**or body_chords_html**/
                    Song song = new Song(title, artist, content);
                    songs.add(song);
                } catch (JSONException e) {
                    Log.d("exception", "tracksinfo");
                    e.printStackTrace();
                }
                //maybe leave out artist option: irrelevant information.
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("extractor", "x" + songs);
        return songs;

    }
}
