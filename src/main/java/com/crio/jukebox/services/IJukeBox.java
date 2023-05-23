package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;


public interface IJukeBox {
    Song playPlaylist(String userId, String playlistId) throws UserNotFoundException, PlaylistNotFoundException;
    Song playSong(String userId, String action) throws UserNotFoundException, PlaylistNotFoundException, SongNotFoundException, InvalidOperationException;

}