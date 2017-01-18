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
        try {
            JSONObject jsonwholething = new JSONObject(mResults);
            JSONArray jresults = (JSONArray) jsonwholething.get("objects");
            for (int i = 0; i < jresults.length(); i++) {
                // for all songs, extract info from JSONArray, put in one song, add song to arraylist.
                try {
                    JSONObject result = jresults.getJSONObject(i);
                    String title = result.getString("title");
                    String content = result.getString("body"); /**or body_chords_html**/
                    //   String content = result.getString("body_chords_html");

                    JSONArray jsonSong = (JSONArray) result.get("authors");
                    int numberAuthors = jsonSong.length();
                    String artist = null;
                    for (int j = 0; j < numberAuthors; j ++){
                        JSONObject jAuthor = (JSONObject) jsonSong.get(j);
                        artist = artist + jAuthor.getString("name");
                    }

                    Song song = new Song(title, artist, content);
                    songs.add(song);
                } catch (JSONException e) {
                    Log.d("JSON", "tracksinfo");
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            Log.d("JSON", "catch");
            e.printStackTrace();
        }
        return songs;

    }
}
