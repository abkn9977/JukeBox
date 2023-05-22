package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends ID{
    private String name;
    private List<String> playlists;
    public User(User user){
        this(user.getId(), user.getName(), user.getPlaylists());
    }

    public User(String id, String name){
        this(name);
        this.id = id;
    }

    public User(String id, String name, List<String> playlists){
        this.id = id;
        this.name = name;
        this.playlists = playlists;
    }

    public User(String name){
        this.name = name;
        this.playlists = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public List<String> getPlaylists() {
        return this.playlists;
    }

    public void setPlaylists(List<String> playlists) {
        this.playlists = playlists;
    }

    /**
     * Checks if user has a playlist with given id
     * @param id id of a playlist that we want to search
     * @return boolean
     */
    public boolean hasPlaylist(String id){
        return this.playlists.contains(id);
    }

    public void removePlaylist(String id){
        this.playlists.remove(id);
    }

    @Override
    public String toString() {
        return "" + this.id + " " + this.name;
    }
}