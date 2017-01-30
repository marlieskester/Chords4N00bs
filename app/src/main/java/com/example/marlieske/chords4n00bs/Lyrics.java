package com.example.marlieske.chords4n00bs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Marlieske on 25-1-2017.
 * custom object Lyrics contains for each song-line the chords and lyrics.
 * Implements parcelable for passing lyrics between activities.
 */

class Lyrics implements Parcelable {
    String songtext;
    ArrayList<String> chord;

    /**constructor**/
    Lyrics(String songtext, ArrayList chord){
        this.songtext = songtext;
        this.chord = chord;
    }

    /**parcelable functions**/
    private Lyrics(Parcel in) {
        this.songtext = in.readString();
        this.chord = in.readArrayList(null);
    }

    public static final Creator<Lyrics> CREATOR = new Creator<Lyrics>() {
        @Override
        public Lyrics createFromParcel(Parcel in) {
            return new Lyrics(in);
        }

        @Override
        public Lyrics[] newArray(int size) {
            return new Lyrics[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songtext);
        dest.writeList(chord);
    }
}
