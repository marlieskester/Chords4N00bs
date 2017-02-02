package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
 * If showDiagrams is checked, setImage parses the line to find chords, then adds an image for each chord.
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
        Lyrics thisSong = lyrics.get(position);
        String lyrics = thisSong.songtext;
        TVLyrics.setText(lyrics);
        for (int i = 0; i < 7; i++) {
            String viewName = "lyrics_IV_Diagram" + i;
            int imageID = context.getResources().getIdentifier(viewName, "id", context.getPackageName());
            ImageView IVDiagram = (ImageView) convertView.findViewById(imageID);
            IVDiagram.setVisibility(View.GONE);
        }
        if (checked) { setImage(thisSong, convertView); }
        return convertView;
    }

    private void setImage(Lyrics thisSong, View convertView){
        int chordAmount = thisSong.chord.size();
        for (int i = 0; i < chordAmount && i < 7; i++) {
            ScaleChanger changer = new ScaleChanger();
            String firstChord = thisSong.chord.get(i);
            String tempChord = firstChord.substring(1, firstChord.lastIndexOf("]"));
            String finalChord = changer.ChangeScale(tempChord).toLowerCase();
            String viewName = "lyrics_IV_Diagram" + i;
            String picName = "gitaar_" + finalChord;

            int imageID = context.getResources().getIdentifier(viewName, "id", context.getPackageName());
            int picID = context.getResources().getIdentifier(picName, "drawable", context.getPackageName());
            ImageView IVDiagram = (ImageView) convertView.findViewById(imageID);
            Drawable drawable1 = ContextCompat.getDrawable(context, picID);
            IVDiagram.setImageDrawable(drawable1);
            IVDiagram.setVisibility(View.VISIBLE);
        }
    }

    public int getCount() {
        return lyrics.size();
    }
}