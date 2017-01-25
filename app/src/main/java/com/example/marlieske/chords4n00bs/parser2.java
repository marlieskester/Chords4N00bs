package com.example.marlieske.chords4n00bs;

import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Marlieske on 23-1-2017.
 */

public class parser2 {
    public ArrayList<Lyrics> parse(Song song) {
        // TextView lyrics = (TextView) findViewById(R.id.lyricsLyrics);


        BufferedReader reader = new BufferedReader(new StringReader(song.content));
        ArrayList<Lyrics> playsong = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null) {
                String songtext = null;
                ArrayList chords = new ArrayList<>();
                Lyrics newLyrics = new Lyrics(songtext, chords);

                while (line.contains("[")) {
                    int startChord = line.indexOf("[");
                    int endChord = line.indexOf("]") + 1;
                    String chord = line.substring(startChord, endChord);
                    newLyrics.chord.add(chord);
                    line = line.replace(chord, "_");
                    //line = line.substring(endChord + 1);
                }

                newLyrics.songtext = line;
                line = reader.readLine();
                playsong.add(newLyrics);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("parser2", "parse");
        return playsong;
    }

}