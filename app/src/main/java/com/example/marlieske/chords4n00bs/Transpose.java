//package com.example.marlieske.chords4n00bs;
//
//import android.util.Log;
//
///**
// * Created by Marlieske on 31-1-2017.
// */
//
//public class Transpose {
//    public Transpose(){}
//
//    String ScaleUp(String chord){
//        if (chord.contains("b")) {
//            chord = chord.replace("b", "");
//        } else if (chord.contains("B")) {
//            chord = chord.replace("B", "C");
//        } else if (chord.contains("E")) {
//            chord = chord.replace("E", "F");
//        } else if (chord.contains("#")) {
//            char base = chord.charAt(1);
//            int newbase = base + 1;
//            if (newbase == 48) {
//                newbase = newbase - 7;
//            }
//            chord = chord.replace("#", "");
//            chord = chord.replace(base, (char) newbase);
//        } else {
//            Log.d("transpose", chord);
//            char base = chord.charAt(1);
//            String oldchord = chord.substring(1, 2);
//            int newbase = base + 1;
//            if (newbase == 48) {
//                newbase = newbase - 7;
//            }
//            String x = newbase + "b";
//            chord = chord.replace(oldchord, x);
//            Log.d("transpose2", chord);
//            }
//        return chord;
//    }
//
//    String ScaleDown(String chord){
//        if (chord.contains("#")) {
//            chord = chord.replace("#", "");
//        } else if(chord.contains("C")) {
//            chord = chord.replace("C", "B");
//        } else if (chord.contains("F")) {
//            chord = chord.replace("F", "E");
//        } else if (chord.contains("b")) {
//            char base = chord.charAt(1);
//            int newbase = base - 1;
//            if (newbase == 40) {
//                newbase = newbase + 8;
//            }
//            chord = chord.replace("b", "");
//            chord = chord.replace(base, (char) newbase);
//        } else {
//            Log.d("transpose", chord);
//            char base = chord.charAt(1);
//            String oldchord = chord.substring(1, 2);
//            int newbase = base - 1;
//            if (newbase == 40) {
//                newbase = newbase + 8;
//            }
//            String x = newbase + "#";
//            chord = chord.replace(oldchord, x);
//            Log.d("transpose2", chord);
//        }
//        return chord;
//    }
//
//    //TODO: werkt ie?, lelijke dingen vervagen, fn voor plaatjes maken evt
//
//}
