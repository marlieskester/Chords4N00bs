package com.example.marlieske.chords4n00bs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marlieske on 10-1-2017.
 */

public class Song implements Parcelable {
    String title;
    String artist;
    String content;

    /**Constructor*/
    public Song(String title, String artist, String content) {
        this.title = title;
        this.artist = artist;
        this.content = content;
    }

    /** parcelable functions**/
    private Song(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        this.title = data[0];
        this.artist = data[1];
        this.content = data[2];
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.title,
                this.artist,
                this.content});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

}
