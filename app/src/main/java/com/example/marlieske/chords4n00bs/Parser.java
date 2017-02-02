package com.example.marlieske.chords4n00bs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Marlieske on 23-1-2017.
 * Parser seperates lines of the song, and extracts chords.
 */

class Parser {
    ArrayList<Lyrics> parse(Song song) {
        BufferedReader reader = new BufferedReader(new StringReader(song.content));
        ArrayList<Lyrics> playsong = new ArrayList<>();
        try {
            String line = reader.readLine();

            // add some blank lines for easier use of autoscroll
            for (int i = 0; i < 5; i++) {
                Lyrics lyrics = new Lyrics(" ", new ArrayList());
                playsong.add(lyrics);
            }
            while (line != null) {
               // String songtext = null;
              //  ArrayList chords = new ArrayList<>();
                Lyrics newLyrics = new Lyrics(null, new ArrayList());

                while (line.contains("[")) {
                    int startChord = line.indexOf("[");
                    int endChord = line.indexOf("]") + 1;
                    String chord = line.substring(startChord, endChord);
                    newLyrics.chord.add(chord);
                    line = line.replaceFirst(Pattern.quote(chord), "_");
                }

                newLyrics.songtext = line;
                line = reader.readLine();
                playsong.add(newLyrics);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playsong;
    }

}