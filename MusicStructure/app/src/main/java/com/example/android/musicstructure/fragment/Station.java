package com.example.android.musicstructure.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.musicstructure.PlayListActivity;
import com.example.android.musicstructure.R;
import com.example.android.musicstructure.StationActivity;

/**
 * Created by akueisara on 9/25/2016.
 */
public class Station extends Fragment {
    ArrayAdapter<String> mAdapter;
    String[] items = {"Station 1", "Station 2", "Station 3", "Station 4"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View station = inflater.inflate(R.layout.frag_station, container, false);
        GridView gridView = (GridView) station.findViewById(R.id.gridView);

        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(getActivity(), StationActivity.class);
                                                intent.putExtra("station", items[position]);
                                                startActivity(intent);
                                            }
                                        }
        );
        return station;
    }
}