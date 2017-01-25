package com.example.marlieske.chords4n00bs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Marlieske on 25-1-2017.
 */

public class Lyrics implements Parcelable {
    String songtext;
    ArrayList<String> chord;
    // String DiagramLink;

    /**constructor class**/
    public Lyrics(String songtext, ArrayList chord){
        this.songtext = songtext;
        this.chord = chord;
    }


    protected Lyrics(Parcel in) {
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
