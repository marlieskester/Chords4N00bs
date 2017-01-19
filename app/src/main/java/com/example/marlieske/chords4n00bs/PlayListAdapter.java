package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Marlieske on 18-1-2017.
 */

public class PlayListAdapter extends ArrayAdapter<PlayActivity.Lyrics> {
    ArrayList<PlayActivity.Lyrics> lyrics;

    public PlayListAdapter(Context context, int resource, ArrayList<PlayActivity.Lyrics> lyrics) {
        super(context, resource, lyrics);
        this.lyrics = lyrics;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lyrics, null);
        }

        TextView TVLyrics = (TextView) convertView.findViewById(R.id.lyricsLyrics);
        TextView TVChords = (TextView) convertView.findViewById(R.id.lyricsChord);
        ImageView IVDiagram = (ImageView) convertView.findViewById(R.id.lyricsDiagram);

        PlayActivity.Lyrics thisSong = lyrics.get(position);
        TVLyrics.setText(thisSong.songtext);
        int chordAmount = thisSong.chord.size();
        String chords = "";
        for (int i = 0; i < chordAmount; i++) {
            chords = chords + ", " + thisSong.chord.get(i);
        }
        TVChords.setText(chords);
        if (chords.equals("")){
            IVDiagram.setVisibility(View.GONE);
        } else {
            //TODO img nog doen
        }

        return convertView;
    }


    public int getCount() {
        return lyrics.size();
    }
}