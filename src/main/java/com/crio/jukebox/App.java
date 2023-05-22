package com.crio.jukebox;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongService;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class App {
    // To run the application ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
    public static void main(String[] args) {
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT-FILE";
        String actualSequence =
                commandLineArgs.stream().map(a -> a.split("=")[0]).collect(Collectors.joining("$"));
        if (expectedSequence.equals(actualSequence)) {
            run(commandLineArgs);
        }
    }

    public static void run(List<String> commandLineArgs) {
        // Complete the final logic to run the complete program.


    }
}
