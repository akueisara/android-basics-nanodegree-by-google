package com.example.android.musicstructure.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.musicstructure.R;

/**
 * Created by akueisara on 9/25/2016.
 */
public class Artist extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View artist = inflater.inflate(R.layout.frag_artist, container, false);
        ((TextView) artist.findViewById(R.id.textView)).setText("Artists");
        return artist;
    }
}