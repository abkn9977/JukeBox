package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.UnsupportedFormatException;
import com.crio.jukebox.repositories.ISongRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongService implements ISongService{
    ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getAllSongs() {
        return new ArrayList<>(songRepository.findAll());
    }

    public Song getSongById(String id){
        return songRepository.findById(id);
    }

    /**
     * Load songs from a csv file and store in song repository
     * @param path path of the csv file
     * @return successful message
     * @author Aftab
     * @since 1.0
     */
    @Override
    public String loadSongs(String path) throws IOException, CsvValidationException, InvalidOperationException, UnsupportedFormatException {
        if(path.isEmpty())
            throw new InvalidOperationException("File path can not be empty");

        String fileFormat = "csv";
        if(!fileFormat.equals(path.split("\\.")[1]))
            throw new UnsupportedFormatException("Unsupported file type" + path.split("\\.")[1] + "! Input a csv file");

        File file = new File(path);
        FileReader fr = new FileReader(file);
        CSVReader csvReader = new CSVReader(fr);

        String[] song;

        while ((song = csvReader.readNext()) != null) {

            if(song.length > 5){
                String name = song[1];
                String genre = song[2];
                String albumName = song[3];
                String artist = song[4];
                List<String> featuredArtists = List.of(song[5].split("#"));

                songRepository.save(new Song(name, genre, albumName, artist, featuredArtists));
            }
        }

        return "Songs Loaded successfully";
    }
}