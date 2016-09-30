package com.example.android.musicstructure.utils;

/**
 * Created by akueisara on 9/29/2016.
 */
public class Artist {
    private String mArtistName;
    private Song[] mSongs;
    private Album[] mAlbums;

    public Artist(String artistName, Song[] songs, Album[] albums) {
        mArtistName = artistName;
        mSongs = songs;
        mAlbums = albums;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
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

    public Album[] getAlbums() {
        return mAlbums;
    }

    public void setAlbums(Album[] albums) {
        mAlbums = albums;
    }

    public String[] getAlbumNames() {
        int len = mAlbums.length;
        String[] albumNames = new String[len];
        for(int i=0; i<len; i++) {
            albumNames[i] = mAlbums[i].getAlbumName();
        }
        return albumNames;
    }

    @Override
    public String toString() {
        return mArtistName;
    }
}
