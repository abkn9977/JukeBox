package com.crio.jukebox;

import com.crio.jukebox.appConfigs.AppConfig;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongService;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class App {
    // To run the application ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
    public static void main(String[] args) {
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT_FILE";
        String actualSequence =
                commandLineArgs.stream().map(a -> a.split("=")[0]).collect(Collectors.joining("$"));
        if (expectedSequence.equals(actualSequence)) {
            run(commandLineArgs);
        }
    }

    public static void run(List<String> commandLineArgs) {
        // Complete the final logic to run the complete program.
        AppConfig appConfig = new AppConfig();
        CommandInvoker commandInvoker = appConfig.getCommandInvoker();

        BufferedReader reader;
        String input = commandLineArgs.get(0).split("=")[1];
        commandLineArgs.remove(0);

        try{
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while(line != null){
                List<String> tokens = Arrays.asList(line.split(" "));

                //execute command
                commandInvoker.executeCommand(tokens.get(0), tokens);

                line = reader.readLine();
            }
        } catch (NoSuchCommandException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
