package com.example.android.musicstructure.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.musicstructure.fragment.AlbumFragment;
import com.example.android.musicstructure.fragment.ArtistFragment;
import com.example.android.musicstructure.fragment.PlaylistFragment;
import com.example.android.musicstructure.fragment.SongFragment;

/**
 * Created by akueisara on 9/25/2016.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragement for PlaylistFragment Tab
                return new PlaylistFragment();
            case 1:
                //Fragment for ArtistFragment Tab
                return new ArtistFragment();
            case 2:
                return new AlbumFragment();
            case 3:
                return new SongFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        return 4; //No of Tabs
    }
}
