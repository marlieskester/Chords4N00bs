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

        final TextView TVArtist = (TextView) convertView.findViewById(R.id.artistresult);
        final TextView TVTitle = (TextView) convertView.findViewById(R.id.titleresult);
        final Song song = songs.get(position);
        TVArtist.setText(song.artist);
        TVTitle.setText(song.title);

        //TODO onclick verplaatsen naar activity? losmaken?
        //TODO song of lied?
        TVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song thisOne = songs.get(position);
                String title = thisOne.title;
                String artist = thisOne.artist;
                String content = thisOne.content;

                Intent selectedSong = new Intent(context, SongActivity.class);
                selectedSong.putExtra("song", new Song(title, artist, content));
                selectedSong.putExtra("lied", thisOne);
                context.startActivity(selectedSong);
            }
        });

        TVTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (classname.equals("songbook")) {
                    DatabaseHelper helper = new DatabaseHelper(context);
                    helper.delete(song.title);
                    Toast.makeText(context, "Deleted song from list", Toast.LENGTH_SHORT).show();
                    Intent deletedSong = new Intent(context, MainActivity.class);
                    context.startActivity(deletedSong);
                }
                return true;
            }
        });
        return convertView;
    }

    public int getCount(){
        return songs.size();
    }
}
