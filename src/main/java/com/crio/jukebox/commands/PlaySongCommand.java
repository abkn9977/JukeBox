package com.crio.jukebox.commands;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IJukeBox;

import java.util.List;

public class PlaySongCommand implements ICommand{

    private final IJukeBox jukeBox;

    public PlaySongCommand(IJukeBox jb){
        this.jukeBox = jb;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            Song song = jukeBox.playSong(tokens.get(1), tokens.get(2));
            System.out.print("Current Song Playing\n" + song);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}