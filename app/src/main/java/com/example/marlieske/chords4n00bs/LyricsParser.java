package com.example.marlieske.chords4n00bs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Marlieske on 23-1-2017.
 */

public class LyricsParser {
    public ArrayList<Lyrics> parse(Song song) {
        // TextView lyrics = (TextView) findViewById(R.id.lyricsLyrics);
        BufferedReader reader = new BufferedReader(new StringReader(song.content));
        ArrayList<Lyrics> playsong = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null) {
                Lyrics newLyrics = new Lyrics();
                newLyrics.songtext = line;
                newLyrics.chord = new ArrayList<>();

                while (line.contains("[")) {
                    int startChord = line.indexOf("[") + 1;
                    int endChord = line.indexOf("]");
                    String chord = line.substring(startChord, endChord);
                    newLyrics.chord.add(chord);
                    line = line.substring(endChord + 1);
                }

                line = reader.readLine();
                playsong.add(newLyrics);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playsong;
    }

    class Lyrics {
        String songtext;
        ArrayList<String> chord;
//        String DiagramLink;
    }
}
