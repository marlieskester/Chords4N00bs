package com.example.marlieske.chords4n00bs;

import android.util.Log;

/**
 * Created by Marlieske on 2-2-2017.
 * Class contains all "ugly" functions that change the root of the chord.
 * transpose up, transpose down, and skip-sharp are provided below.
 */

class ScaleChanger {
    /**all chords +.5**/
    String transposeUP(String chord){
        if (chord.contains("b")){
            chord = chord.replace("b", "");
        } else {
            if (chord.contains("A#")) {
                chord = chord.replace("A#", "B");
            } else if (chord.contains("B#")){
                chord = chord.replace("B", "C");
            } else if (chord.contains("C#")) {
                chord = chord.replace("C#", "D");
            } else if (chord.contains("D#")) {
                chord = chord.replace("D#", "E");
            } else if (chord.contains("E#")) {
                chord = chord.replace("E", "F");
            } else if (chord.contains("F#")) {
                chord = chord.replace("F#", "G");
            } else if (chord.contains("G#")) {
                chord = chord.replace("G#", "A");
            } else if (chord.contains("A")) {
                chord = chord.replace("A", "Bb");
            } else if (chord.contains("B")){
                chord = chord.replace("B", "C");
            } else if (chord.contains("C")) {
                chord = chord.replace("C", "C#");
            } else if (chord.contains("D")) {
                chord = chord.replace("D", "Eb");
            } else if (chord.contains("E")) {
                chord = chord.replace("E", "F");
            } else if (chord.contains("F")) {
                chord = chord.replace("F", "F#");
            } else if (chord.contains("G")) {
                chord = chord.replace("G", "Ab");
            }
        }
        return chord;
    }

    /**all chords -.5**/
    String transposeDOWN(String chord){
        if (chord.contains("#")) {
            chord = chord.replace("#", "");
        } else {
            if (chord.contains("Ab")) {
                chord = chord.replace("Ab", "G");
            } else if (chord.contains("Bb")) {
                chord = chord.replace("Bb", "A");
            } else if (chord.contains("Cb")) {
                chord = chord.replace("C", "B");
            } else if (chord.contains("Db")) {
                chord = chord.replace("Db", "C");
            } else if (chord.contains("Eb")) {
                chord = chord.replace("Eb", "D");
            } else if (chord.contains("Fb")) {
                chord = chord.replace("F", "E");
            } else if (chord.contains("Gb")) {
                chord = chord.replace("Gb", "F");
            } else if (chord.contains("A")) {
                chord = chord.replace("A", "G#");
            } else if (chord.contains("B")) {
                chord = chord.replace("B", "Bb");
            } else if (chord.contains("C")) {
                chord = chord.replace("C", "B");
            } else if (chord.contains("D")) {
                chord = chord.replace("D", "C#");
            } else if (chord.contains("E")) {
                chord = chord.replace("E", "Eb");
            } else if (chord.contains("F")) {
                chord = chord.replace("F", "E");
            } else if (chord.contains("G")) {
                chord = chord.replace("G", "F#");
            }
        }
        return chord;
    }

    /**remove the sharp from chord**/
    String ChangeScale(String chord){
        if (chord.contains("A#")) {
            chord = chord.replace("A#", "Bb");
        } else if (chord.contains("B#")){
            chord = chord.replace("B#", "C");
        } else if (chord.contains("C#")) {
            chord = chord.replace("C#", "Db");
        } else if (chord.contains("D#")) {
            chord = chord.replace("D#", "Eb");
        } else if (chord.contains("E#")) {
            chord = chord.replace("E#", "F");
        } else if (chord.contains("F#")) {
            chord = chord.replace("F#", "Gb");
        } else if (chord.contains("G#")) {
            chord = chord.replace("G#", "Ab");
        }
        return chord;
    }
}
