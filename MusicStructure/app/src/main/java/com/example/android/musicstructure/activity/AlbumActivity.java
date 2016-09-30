package com.example.android.musicstructure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.musicstructure.R;
import com.example.android.musicstructure.utils.Album;
import com.example.android.musicstructure.utils.Song;

import java.util.ArrayList;

/**
 * Created by akueisara on 9/29/2016.
 */
public class AlbumActivity extends AppCompatActivity {
    ArrayAdapter<Song> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String albumName = getIntent().getStringExtra("album");
        getSupportActionBar().setTitle(albumName);

        final ArrayList<Song> items = new ArrayList<Song>();
        Song song1 = new Song("Song 1", "Artist 1", "Album 1");
        Song song2 = new Song("Song 2", "Artist 2", "Album 2");
        Song song3 = new Song("Song 3", "Artist 1", "Album 1");
        Song song4 = new Song("Song 4", "Artist 2", "Album 2");
        Album album1 = new Album("Album 1", new Song[]{song1, song3});
        Album album2 = new Album("Album 2", new Song[]{song2, song4});
        if(albumName.equals(album1.getAlbumName())) {
            Song[] songs = album1.getSongs();
            for(Song s: songs) {
                items.add(s);
            }
        }
        if(albumName.equals(album2.getAlbumName())) {
            Song[] songs = album2.getSongs();
            for(Song s: songs) {
                items.add(s);
            }
        }

        ListView listView = (ListView) findViewById(R.id.listView);

        mAdapter = new ArrayAdapter<Song>(AlbumActivity.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(AlbumActivity.this, NowPlayingActivity.class);
                                                intent.putExtra("song", items.get(position).getName());
                                                startActivity(intent);
                                            }
                                        }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
