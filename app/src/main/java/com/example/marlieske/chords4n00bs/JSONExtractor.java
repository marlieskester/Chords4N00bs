package com.example.marlieske.chords4n00bs;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Marlieske on 10-1-2017.
 */

public class JSONExtractor {
     /**
     * constructor
     */
    public JSONExtractor() {}

    /**
     * extracts info from JSON
     */
    public ArrayList<Song> getSongs(String mResults) {
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
                    for (int j = 0; j < numberAuthors; j++) {
                        JSONObject jAuthor = (JSONObject) jsonSong.get(j);
                        artist = artist + jAuthor.getString("name");
                    }

                    Song song = new Song(title, artist, content);
                    songs.add(song);
                }
                catch (JSONException e){
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

    public Chord getChord(String chords){
        Chord chord = null;
        try {
            JSONObject jsonwholething;
            jsonwholething = new JSONObject(chords);
            JSONArray jresults = (JSONArray) jsonwholething.get("objects");
            JSONObject result = jresults.getJSONObject(0);
            String name = result.getString("name");
            String imgurl = result.getString("image_url");
            chord = new Chord(name, imgurl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chord;
    }
}
