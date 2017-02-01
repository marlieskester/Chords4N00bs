package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marlieske on 18-1-2017.
 */

class PlayListAdapter extends ArrayAdapter<Lyrics> /**implements onFinished **/{
    private ArrayList<Lyrics> lyrics;
    private boolean checked;
    private Context context;

    PlayListAdapter(Context context, int resource, ArrayList<Lyrics> lyrics, boolean checked) {
        super(context, resource, lyrics);
        this.lyrics = lyrics;
        this.checked = checked;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_lyrcs_entry, null);
        }

        TextView TVLyrics = (TextView) convertView.findViewById(R.id.lyrics_Lyrics);
        TextView TVChords = (TextView) convertView.findViewById(R.id.lyrics_Chord);
        ImageView IVDiagram0 = (ImageView) convertView.findViewById(R.id.lyrics_IV_Diagram0);
        ImageView IVDiagram1 = (ImageView) convertView.findViewById(R.id.lyrics_IV_Diagram1);
        ImageView IVDiagram2 = (ImageView) convertView.findViewById(R.id.lyrics_IV_Diagram2);
        ImageView IVDiagram3 = (ImageView) convertView.findViewById(R.id.lyrics_IV_Diagram3);
        ImageView IVDiagram4 = (ImageView) convertView.findViewById(R.id.lyrics_IV_Diagram4);
        ImageView IVDiagram5 = (ImageView) convertView.findViewById(R.id.lyrics_IV_Diagram5);
        ImageView IVDiagram6 = (ImageView) convertView.findViewById(R.id.lyrics_IV_Diagram6);



        Lyrics thisSong = lyrics.get(position);
        String lyrics = thisSong.songtext;
        TVLyrics.setText(lyrics);
        //TVLyrics.setText(thisSong.songtext.get(0).toString());
        int chordAmount = thisSong.chord.size();
        if (checked && chordAmount != 0) {
            String chords = "";
            for (int i = 0; i < chordAmount; i++) {
                chords = chords + ", " + thisSong.chord.get(i);
                String chord = thisSong.chord.get(i);
                String rawInput = "R.drawable.guitar_" + chord;
                // ?
//                    byte[] encodeByte = Base64.decode(rawInput, Base64.DEFAULT);

                //Bitmap input = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                //ImageView diagram = new ImageView(getContext());
                int input = R.drawable.gitaar_amaj7;
                //TODO juiste sterretje pakken
                //TODO kruizen handelen
              //  IVDiagram0.setImageResource(input);
                //diagram.setImageBitmap(input);
               // convertView.add(diagram);

//                SearchAsyncTask async = new SearchAsyncTask(context);
//                async.execute(thisSong.chord.get(i), "play");


//                JSONExtractor extractor = new JSONExtractor();
//                Chord chord = extractor.getChord(result);
//                new DownloadImageTask(IVDiagram).execute(chord.imgurl);
            }
            TVChords.setText(chords);

        }
        else {
            TVChords.setVisibility(View.GONE);
            IVDiagram0.setVisibility(View.GONE);
            IVDiagram1.setVisibility(View.GONE);
            IVDiagram2.setVisibility(View.GONE);
            IVDiagram3.setVisibility(View.GONE);
            IVDiagram4.setVisibility(View.GONE);
            IVDiagram5.setVisibility(View.GONE);
            IVDiagram6.setVisibility(View.GONE);
        }
        return convertView;
    }


    public int getCount() {
        return lyrics.size();
    }
}