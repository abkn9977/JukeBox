package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

import java.util.List;

public class PlaylistService implements IPlaylistService{
    IPlaylistRepository playlistRepository;
    IUserRepository userRepository;
    ISongRepository songRepository;

    public PlaylistService(IPlaylistRepository playlistRepository, IUserRepository userRepository, ISongRepository songRepository){
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public Playlist createPlaylist(String userId, String playlistName, List<String> songIds) throws UserNotFoundException, SongNotFoundException {
        User creater = userRepository.findById(userId);

        if(creater == null)
            throw new UserNotFoundException("User with id " + userId + " not found!");

        //validate if the songIds are present or not in repo
        if(!validateSongIds(songIds))
            throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");

        //save playlist to repo
        Playlist playlist = playlistRepository.save(new Playlist(playlistName, creater, songIds));

        //add newly create playlist to user's list of playlist
        creater.getPlaylists().add(playlist.getId());

        return playlist;
    }

    @Override
    public Playlist deletePlaylist(String userId, String playlistId) throws UserNotFoundException, PlaylistNotFoundException {

        User user = userRepository.findById(userId);

        if(user == null)
            throw new UserNotFoundException("User with id " + userId + " not found!");

        if(!user.hasPlaylist(playlistId))
            throw new PlaylistNotFoundException("Playlist Not Found");

        //remove playlist from user
        user.removePlaylist(playlistId);

        //remove playlist from repo
        Playlist playlist = playlistRepository.findById(playlistId);
        playlistRepository.delete(playlist);

        return playlist;
    }

    @Override
    public Playlist modifyPlaylist(String actionCommand, String userId, String playlistId, List<String> songIds) throws UserNotFoundException, PlaylistNotFoundException, SongNotFoundException, InvalidOperationException {

        User user = userRepository.findById(userId);

        if(user == null)
            throw new UserNotFoundException("User with id " + userId + " not found!");

        if(!user.hasPlaylist(playlistId))
            throw new PlaylistNotFoundException("Playlist Not Found");

        Playlist playlist = playlistRepository.findById(playlistId);

        //add or delete song according to command
        switch (actionCommand){
            case "ADD-SONG":
                if(!validateSongIds(songIds))
                    throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");

                for(String songId: songIds)
                    playlist.addSong(songId);
                break;

            case "DELETE-SONG":
                if(!validateSongIds(playlist.getSongs(), songIds))
                    throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.\n");

                for(String songId: songIds){
                    playlist.deleteSong(songId);
                }
                break;
            default: throw new InvalidOperationException("Invalid modify command");
        }

        return playlist;
    }

    public boolean validateSongIds(List<String> songIds){
        for(String songId: songIds){
            if(songRepository.findById(songId) == null)
                return false;
        }

        return true;
    }

    public boolean validateSongIds(List<String> songs, List<String> songIds){
        for(String songId: songIds){
            if(!songs.contains(songId))
                return false;
        }

        return true;
    }
}