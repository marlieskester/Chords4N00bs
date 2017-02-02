package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marlieske on 18-1-2017.
 * Playlistadapter shows content of song as parsed by Parser.
 * Reason to use listview is the option for automatic scroll and insert images in a structured way.
 *
 */

class PlayListAdapter extends ArrayAdapter<Lyrics> {
    private ArrayList<Lyrics> lyrics;
    private boolean checked;
    private Context context;

    PlayListAdapter(Context context, int resource, ArrayList<Lyrics> lyrics, boolean checked) {
        super(context, resource, lyrics);
        this.lyrics = lyrics;
        this.checked = checked;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_lyrcs_entry, null);
        }

        TextView TVLyrics = (TextView) convertView.findViewById(R.id.lyrics_Lyrics);
        TextView TVChords = (TextView) convertView.findViewById(R.id.lyrics_Chord);

        Lyrics thisSong = lyrics.get(position);
        String lyrics = thisSong.songtext;
        TVLyrics.setText(lyrics);

        return convertView;
    }


    public int getCount() {
        return lyrics.size();
    }
}