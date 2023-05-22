package com.crio.jukebox.entities;

import java.util.List;

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

    //checks if this playlist has song with given ID
    public boolean hasSong(String id){
        return this.songs.contains(id);
    }
}