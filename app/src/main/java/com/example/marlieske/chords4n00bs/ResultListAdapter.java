package com.example.marlieske.chords4n00bs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.marlieske.chords4n00bs.R.string.song;

/**
 * Created by Marlieske on 10-1-2017.
 * Adapter shapes list in the intended way. Offers onclick option to view song information and play,
 * and in case of songbook also offers delete option.
 */

class ResultListAdapter extends ArrayAdapter<Song> {
    private Context context;
    private ArrayList<Song> songs;
    private String classname;

    /**constructor**/
    ResultListAdapter(Context context, int resource, ArrayList<Song> songs, String classname) {
        super(context, resource, songs);
        this.songs = songs;
        this.context = context;
        this.classname = classname;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent){

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_result_entry, null);
        }

        final TextView TVArtist = (TextView) convertView.findViewById(R.id.result_artist);
        final TextView TVTitle = (TextView) convertView.findViewById(R.id.result_title);
        final Song song = songs.get(position);
        TVArtist.setText(song.artist);
        TVTitle.setText(song.title);

        TVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passSong(position);
            }
        });

        TVTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteSong(position);
                return true;
            }
        });
        return convertView;
    }

    public int getCount(){
        return songs.size();
    }

    private void passSong(int position){
        Song thisOne = songs.get(position);
        String title = thisOne.title;
        String artist = thisOne.artist;
        String content = thisOne.content;

        Intent selectedSong = new Intent(context, SongActivity.class);
        selectedSong.putExtra("song", new Song(title, artist, content));
        context.startActivity(selectedSong);
    }

    private void deleteSong(int position){
        if (classname.equals("songbook")) {
            DatabaseHelper helper = new DatabaseHelper(context);
            helper.delete(songs.get(position).title);
            Toast.makeText(context, "Deleted song from list", Toast.LENGTH_SHORT).show();
            Intent deletedSong = new Intent(context, MainActivity.class);
            context.startActivity(deletedSong);
        }
    }
}
