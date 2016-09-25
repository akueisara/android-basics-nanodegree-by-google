package com.example.android.musicstructure.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.musicstructure.fragment.Album;
import com.example.android.musicstructure.fragment.Artist;
import com.example.android.musicstructure.fragment.Genre;
import com.example.android.musicstructure.fragment.Playlist;
import com.example.android.musicstructure.fragment.Song;
import com.example.android.musicstructure.fragment.Station;

/**
 * Created by akueisara on 9/25/2016.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragement for Playlist Tab
                return new Playlist();
            case 1:
                //Fragment for Station Tab
                return new Station();
            case 2:
                //Fragment for Artist Tab
                return new Artist();
            case 3:
                return new Album();
            case 4:
                return new Song();
            case 5:
                return new Genre();
        }
        return null;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 6; //No of Tabs
    }
}
