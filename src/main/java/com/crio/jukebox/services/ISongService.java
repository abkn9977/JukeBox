package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.UnsupportedFormatException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

public interface ISongService {
    List<Song> getAllSongs();

    Song getSongById(String id);
    String loadSongs(String file) throws IOException, CsvValidationException, InvalidOperationException, UnsupportedFormatException;
}
