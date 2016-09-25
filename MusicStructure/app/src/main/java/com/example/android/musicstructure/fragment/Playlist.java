package com.example.android.musicstructure.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.musicstructure.PlayListActivity;
import com.example.android.musicstructure.R;

/**
 * Created by akueisara on 9/25/2016.
 */
public class Playlist extends Fragment {
    ArrayAdapter<String> mAdapter;
    String[] items = {"Playlist 1", "Playlist 2", "Playlist 3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View playlist = inflater.inflate(R.layout.frag_playlist, container, false);
        //        ((TextView) playlist.findViewById(R.id.textView)).setText("Playlists");
        ListView listView = (ListView) playlist.findViewById(R.id.listView);

        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                            Intent intent = new Intent(getActivity(), PlayListActivity.class);
                                            intent.putExtra("playlist", items[position]);
                                            startActivity(intent);
                                        }
                                    }
        );
        return playlist;
    }
}
