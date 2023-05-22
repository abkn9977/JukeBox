package com.crio.jukebox.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("User Test Unit")
public class UserTest {

    @Test
    @DisplayName("hasPlaylist should return true")
    public void hasPlaylist_return_true(){
        //arrange
        List<String> playlist = new ArrayList<>(List.of("1"));
        User user = new User("1", "user1", playlist);

        //act and assert
        assertTrue(user.hasPlaylist("1"));
    }

    @Test
    @DisplayName("hasPlaylist should return true")
    public void hasPlaylist_return_false(){
        //arrange
        List<String> playlist = new ArrayList<>(List.of("1"));
        User user = new User("1", "user1", playlist);

        //act and assert
        assertFalse(user.hasPlaylist("2"));
    }
}