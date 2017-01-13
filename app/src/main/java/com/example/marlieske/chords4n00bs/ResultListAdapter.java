package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marlieske on 10-1-2017.
 */
public class ResultListAdapter extends ArrayAdapter<Song> {

    private Context context;
    private ArrayList<Song> songs;

    // constructor
    public ResultListAdapter(Context context, int resource, ArrayList<Song> songs, String classname) {
        super(context, resource, songs);
        this.songs = songs;
        this.context = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        // if no line in listview is available, make a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_layout, null);
        }

        // find textviews
        final TextView TVArtist = (TextView) convertView.findViewById(R.id.artistresult);
        final TextView TVTitle = (TextView) convertView.findViewById(R.id.titleresult);

        // add text to textview
        final Song song = songs.get(position);
        TVArtist.setText(song.artist);
        TVTitle.setText(song.title);

        // onclick pass Song song to next activity
        TVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectedSong = new Intent(context, SongActivity.class);
                Song thisOne = songs.get(position);
                String artist = thisOne.artist;
                String title = thisOne.title;
                String content = thisOne.content;

                selectedSong.putExtra("song", new Song(title, artist, content));
                context.startActivity(selectedSong);
            }
        });
        return convertView;
    }

    public int getCount(){
        return songs.size();
    }
}
