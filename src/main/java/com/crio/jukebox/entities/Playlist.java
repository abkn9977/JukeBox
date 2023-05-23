package com.crio.jukebox.entities;

import java.util.List;
import java.util.Objects;

public class Playlist extends ID{
    private String name;
    private User creator;
    private List<String> songs;

    public Playlist(Playlist playlist){
        this(playlist.getId(), playlist.getName(), playlist.getCreator(), playlist.getSongs());
    }

    public Playlist(String name, User user, List<String> songs){
        this.name = name;
        this.creator = user;
        this.songs = songs;
    }

    public Playlist(String id, String name, User user, List<String> songs){
        this(name, user, songs);
        this.id = id;
    }

    public void addSong(String songId){
        if(!hasSong(songId))
            this.songs.add(songId);
    }

    public void deleteSong(String songId){
        this.songs.remove(songId);
    }

    //check if songs list have a song ID
    public boolean hasSong(String songId){
        return this.songs.contains(songId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Playlist playlist = (Playlist) o;

        if (!Objects.equals(name, playlist.name)) return false;
        if (!Objects.equals(creator, playlist.creator)) return false;
        return Objects.equals(songs, playlist.songs);
    }

    @Override
    public String toString() {
        return "Playlist ID - " + this.id + "\n" +
                "Playlist Name - " + this.name + "\n" +
                "Song IDs - " + String.join(" ", this.songs) + "\n";
    }
}