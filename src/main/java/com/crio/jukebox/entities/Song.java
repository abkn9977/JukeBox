package com.crio.jukebox.entities;

import java.util.List;

public class Song extends ID{
    private String name;
    private String genre;
    private String albumName;
    private String artist;
    private List<String> featuredArtists;

    public Song(Song song){
        this(song.id, song.name, song.genre, song.albumName, song.artist, song.featuredArtists);
    }

    public Song(String id, String name, String genre, String albumName, String artist, List<String> featuredArtist) {
        this(name, genre, albumName, artist, featuredArtist);
        this.id = id;
    }

    public Song(String name, String genre, String albumName, String artist, List<String> featuredArtist) {
        this.name = name;
        this.genre = genre;
        this.albumName = albumName;
        this.artist = artist;
        this.featuredArtists = featuredArtist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getFeaturedArtists() {
        return featuredArtists;
    }

    public void setFeaturedArtists(List<String> featuredArtists) {
        this.featuredArtists = featuredArtists;
    }

    @Override
    public String toString() {
        return "Song - " + this.getName() + "\n" +
                "Album - " + this.getAlbumName() + "\n" +
                "Artists - " + String.join(",", this.getFeaturedArtists()) + "\n";
    }
}