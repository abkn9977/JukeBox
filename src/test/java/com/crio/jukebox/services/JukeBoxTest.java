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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("JukeBox Test Unit")
@ExtendWith(MockitoExtension.class)
public class JukeBoxTest {

    @Mock
    ISongRepository songRepository;

    @Mock
    IUserRepository userRepository;

    @Mock
    IPlaylistRepository playlistRepository;

    @InjectMocks
    JukeBox jukeBox;

    @Test
    @DisplayName("Should return song")
    public void playPlaylist_shouldReturn_Song() throws UserNotFoundException, PlaylistNotFoundException {
        //arrange
        User user = new User("1", "user1");
        user.getPlaylists().add("1");

        Playlist pl = new Playlist("1", "pl1", user, List.of("1", "2", "3"));
        Song expected = new Song("1", "song1", "pop", "one", "Amir", List.of("Amir"));

        when(userRepository.findById(anyString())).thenReturn(user);
        when(playlistRepository.findById(anyString())).thenReturn(pl);
        when(songRepository.findById(anyString())).thenReturn(expected);

        //act
        Song actual = jukeBox.playPlaylist("1", "1");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return song with ID")
    public void playSong_shouldReturn_SongWithID() throws UserNotFoundException, PlaylistNotFoundException, InvalidOperationException, SongNotFoundException {
        //arrange
        User user = new User("1", "user1");
        user.getPlaylists().add("1");

        Playlist pl = new Playlist("1", "pl1", user, List.of("1", "2", "3"));
        Song expected = new Song("2", "song1", "pop", "one", "Amir", List.of("Amir"));

        jukeBox.setActive(pl);

        when(userRepository.findById(anyString())).thenReturn(user);
        when(songRepository.findById(anyString())).thenReturn(expected);

        //act
        Song actual = jukeBox.playSong("1", "2");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should throw error, song not in playlist")
    public void playSong_shouldThrow_exception() throws UserNotFoundException, PlaylistNotFoundException, InvalidOperationException, SongNotFoundException {
        //arrange
        User user = new User("1", "user1");
        user.getPlaylists().add("1");

        Playlist pl = new Playlist("1", "pl1", user, List.of("1", "2", "3"));

        jukeBox.setActive(pl);

        when(userRepository.findById(anyString())).thenReturn(user);

        //assert
        assertThrows(SongNotFoundException.class, () -> jukeBox.playSong("1", "5"));
    }

    @Test
    @DisplayName("Should return next song")
    public void playSong_shouldReturn_nextSong() throws UserNotFoundException, PlaylistNotFoundException, InvalidOperationException, SongNotFoundException {
        //arrange
        User user = new User("1", "user1");
        user.getPlaylists().add("1");

        Playlist pl = new Playlist("1", "pl1", user, List.of("1", "2", "3"));
        Song expected = new Song("3", "song1", "pop", "one", "Amir", List.of("Amir"));

        jukeBox.setActive(pl);
        jukeBox.setCurrSong(1);

        when(userRepository.findById(anyString())).thenReturn(user);
        when(songRepository.findById(anyString())).thenReturn(expected);

        //act
        Song actual = jukeBox.playSong("1", "NEXT");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return prev song")
    public void playSong_shouldReturn_prevSong() throws UserNotFoundException, PlaylistNotFoundException, InvalidOperationException, SongNotFoundException {
        //arrange
        User user = new User("1", "user1");
        user.getPlaylists().add("1");

        Playlist pl = new Playlist("1", "pl1", user, List.of("1", "2", "3"));
        Song expected = new Song("2", "song1", "pop", "one", "Amir", List.of("Amir"));

        jukeBox.setActive(pl);
        jukeBox.setCurrSong(2);

        when(userRepository.findById(anyString())).thenReturn(user);
        when(songRepository.findById(anyString())).thenReturn(expected);

        //act
        Song actual = jukeBox.playSong("1", "BACK");

        //assert
        assertEquals(expected, actual);
    }
}