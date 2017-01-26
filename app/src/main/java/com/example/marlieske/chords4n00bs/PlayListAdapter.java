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

public class PlayListAdapter extends ArrayAdapter<Lyrics> {
    ArrayList<Lyrics> lyrics;
    boolean checked;
    Context context;

    public PlayListAdapter(Context context, int resource, ArrayList<Lyrics> lyrics, boolean checked) {
        super(context, resource, lyrics);
        this.lyrics = lyrics;
        this.checked = checked;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lyrics, null);
        }

        TextView TVLyrics = (TextView) convertView.findViewById(R.id.lyricsLyrics);
        TextView TVChords = (TextView) convertView.findViewById(R.id.lyricsChord);
        ImageView IVDiagram = (ImageView) convertView.findViewById(R.id.lyricsDiagram);

        Lyrics thisSong = lyrics.get(position);
        String lyrics = thisSong.songtext;
        TVLyrics.setText(lyrics);
        //TVLyrics.setText(thisSong.songtext.get(0).toString());

        if (checked) {
            int chordAmount = thisSong.chord.size();
            String chords = "";
            for (int i = 0; i < chordAmount; i++) {
                chords = chords + ", " + thisSong.chord.get(i);
       //         HTTPRequestHelper helper = new HTTPRequestHelper();
       //         String result = helper.executeRequest(thisSong.chord.get(i), "listadapter");

                SearchAsyncTask async = new SearchAsyncTask(context);
                async.execute(thisSong.chord.get(i), "play");
                JSONExtractor extractor = new JSONExtractor();
              //  Chord chord = extractor.getChord(result);
              //  new DownloadImageTask(IVDiagram).execute(chord.imgurl);
            }
            TVChords.setText(chords);

        }
        else {
            TVChords.setVisibility(View.GONE);
            IVDiagram.setVisibility(View.GONE);
        }

        return convertView;
    }


    public int getCount() {
        return lyrics.size();
    }
}