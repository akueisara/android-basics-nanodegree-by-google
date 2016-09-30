package com.example.android.musicstructure.utils;

/**
 * Created by akueisara on 9/29/2016.
 */
public class Song {
    private String mName;
    private String mArtist;
    private String mAlbum;
    private Playlist[] mPlaylist;

    public Song(String name, String artist, String album) {
        this.mName = name;
        this.mArtist = artist;
        this.mAlbum = album;
    }

    public Song(String name, String artist, String album, Playlist [] playlist) {
        this.mName = name;
        this.mArtist = artist;
        this.mAlbum = album;
        this.mPlaylist = playlist;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public Playlist[] getPlaylist() {
        return mPlaylist;
    }

    public void setPlaylist(Playlist [] playlist) {
        mPlaylist = playlist;
    }

    @Override
    public String toString() {
        return mName + " (by " +  mArtist + " from " + mAlbum + ")";
    }
}
