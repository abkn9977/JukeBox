package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;

import java.util.List;

public interface IPlaylistService {
    Playlist createPlaylist(String userId, String playlistName, List<String> songIds) throws UserNotFoundException, SongNotFoundException;
    Playlist deletePlaylist(String userId, String playlistId) throws UserNotFoundException, PlaylistNotFoundException;

    boolean validateSongIds(List<String> songIds);
}