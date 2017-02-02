package com.example.marlieske.chords4n00bs;

/**
 * Created by Marlieske on 24-1-2017.
 * Custom object chord, accessed from ChordActivity, asynctask and JSONExtractor.
 */

class Chord {
    String name;
    String imgurl;

    /**Constructor*/
    Chord(String name, String imgurl) {
        this.name = name;
        this.imgurl = imgurl;
    }
}
