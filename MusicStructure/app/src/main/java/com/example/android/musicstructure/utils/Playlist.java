package com.example.android.musicstructure.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by akueisara on 9/29/2016.
 */
public class Playlist {
    private String mPlaylistName;
    private Song[] mSongs;

    public Playlist(String playlistName) {
        mPlaylistName = playlistName;
    }

    public Playlist(String playlistName, Song[] songs) {
        mPlaylistName = playlistName;
        mSongs = songs;
    }

    public String getPlaylistName() {
        return mPlaylistName;
    }

    public void setPlaylistName(String playlistName) {
        mPlaylistName = playlistName;
    }

    public Song[] getSongs() {
        return mSongs;
    }

    public void setSongs(Song[] songs) {
        mSongs = songs;
    }

    public String[] getSongNames() {
        int len = mSongs.length;
        String[] songNames = new String[len];
        for(int i=0; i<len; i++) {
            songNames[i] = mSongs[i].getName();
        }
        return songNames;
    }

    @Override
    public String toString() {
        return mPlaylistName;
    }
}
