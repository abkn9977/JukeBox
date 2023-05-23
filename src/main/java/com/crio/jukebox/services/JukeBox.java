package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

import java.util.List;

public class JukeBox implements IJukeBox{

    private Playlist active;
    private Integer currSong;

    private final IUserRepository userRepository;
    private final ISongRepository songRepository;
    private final IPlaylistRepository playlistRepository;

    public JukeBox(IUserRepository ur, ISongRepository sr, IPlaylistRepository pr){
        this.currSong = 0;

        this.userRepository = ur;
        this.songRepository = sr;
        this.playlistRepository = pr;
    }

    @Override
    public Song playPlaylist(String userId, String playlistId) throws UserNotFoundException, PlaylistNotFoundException {
        User user = userRepository.findById(userId);

        if(user == null)
            throw new UserNotFoundException("User with id " + userId + " not found!");

        if(!user.hasPlaylist(playlistId))
            throw new PlaylistNotFoundException("User doesn't have this playlist");

        Playlist playlist = playlistRepository.findById(playlistId);

        if(playlist.getSongs().isEmpty())
            throw new PlaylistNotFoundException("Playlist is empty.");

        this.active = playlist;
        this.currSong = 0;

        return songRepository.findById(playlist.getSongs().get(currSong));
    }

    @Override
    public Song playSong(String userId, String action) throws UserNotFoundException, PlaylistNotFoundException, SongNotFoundException, InvalidOperationException {
        User user = userRepository.findById(userId);

        if(user == null)
            throw new UserNotFoundException("User with id " + userId + " not found!");

        if(this.active == null || user != this.active.getCreator())
            throw new PlaylistNotFoundException("User has no active playlist");

        List<String> songs = this.active.getSongs();
        Song song = null;

        //if request is to play song with given ID
        if(action.matches("[0-9]+")){

            if(!songs.contains(action))
                throw new SongNotFoundException("Given song id is not a part of the active playlist");

            this.currSong = songs.indexOf(action);
            return songRepository.findById(action);
        }

        //if request is to switch NEXT/PREV
        switch (action){
            case "NEXT":
                this.currSong = ++ this.currSong % songs.size();
                return songRepository.findById(songs.get(this.currSong));
            case "BACK":
                this.currSong --;
                if(this.currSong < 0)
                    this.currSong = songs.size() - 1;
                return songRepository.findById(songs.get(this.currSong));
            default:
                throw new InvalidOperationException("Invalid command!");
        }
    }

    public Playlist getActive() {
        return active;
    }

    public void setActive(Playlist active) {
        this.active = active;
    }

    public Integer getCurrSong() {
        return currSong;
    }

    public void setCurrSong(Integer currSong) {
        this.currSong = currSong;
    }
}