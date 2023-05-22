package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DisplayName("PlaylistService Test Unit")
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

    @Mock
    IUserRepository userRepository;
    @Mock
    IPlaylistRepository playlistRepository;
    @Mock
    ISongRepository songRepository;

    @InjectMocks
    PlaylistService playlistService;

    @Test
    @DisplayName("Creates playlist")
    public void createPlaylist_shoudReturn_Playlist() throws UserNotFoundException, SongNotFoundException {

        //arrange
        User user = new User("1", "user1");
        Playlist expected = new Playlist("pl1", user, List.of("1", "2", "3"));
        Song demoSong = new Song("song1", "g1", "a1", "art", List.of("a1", "a2"));

        when(userRepository.findById(anyString())).thenReturn(user);
        when(songRepository.findById(anyString())).thenReturn(demoSong);
        when(playlistRepository.save(any())).thenReturn(expected);

        //act
        Playlist actual = playlistService.createPlaylist("1", "pl1", List.of("1", "2", "3"));

        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Throws songNotFoundException")
    public void createPlaylist_shouldThrow_SongNotFound() throws UserNotFoundException, SongNotFoundException {

        //arrange
        User user = new User("1", "user1");
        Playlist expected = new Playlist("pl1", user, List.of("1", "2", "3"));
        Song demoSong = new Song("song1", "g1", "a1", "art", List.of("a1", "a2"));

        when(userRepository.findById(anyString())).thenReturn(user);
        when(songRepository.findById(anyString())).thenReturn(null);

        //act
        //assert
        assertThrows(SongNotFoundException.class, () -> playlistService.createPlaylist("1", "pl1", List.of("1", "2", "3")));
    }

    @Test
    @DisplayName("Deletes playlist")
    public void deletePlaylist_shoudReturn_Playlist() throws UserNotFoundException, SongNotFoundException, PlaylistNotFoundException {

        //arrange
        User user = new User("1", "user1");
        Playlist expected = new Playlist("1", "pl1", user, List.of("1", "2", "3"));
        user.getPlaylists().add(expected.getId());

        when(userRepository.findById(anyString())).thenReturn(user);
        when(playlistRepository.findById(anyString())).thenReturn(expected);
        doNothing().when(playlistRepository).delete(any(Playlist.class));

        //act
        Playlist actual = playlistService.deletePlaylist("1", "1");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Throw UserNotFoundException")
    public void deletePlaylist_shoudThrow_UserNotFound() throws UserNotFoundException, SongNotFoundException, PlaylistNotFoundException {
        //arrange
        when(userRepository.findById(anyString())).thenReturn(null);

        //assert
        assertThrows(UserNotFoundException.class, () -> playlistService.deletePlaylist("1", "2"));
    }

    @Test
    @DisplayName("Throws PlayListNotFoundException")
    public void deletePlaylist_shoudThrow_PlaylistNotFound() throws UserNotFoundException, SongNotFoundException, PlaylistNotFoundException {

        //arrange
        User user = new User("1", "user1");
        when(userRepository.findById(anyString())).thenReturn(user);

        //assert
        assertThrows(PlaylistNotFoundException.class, () -> playlistService.deletePlaylist("1", "1"));
    }
}