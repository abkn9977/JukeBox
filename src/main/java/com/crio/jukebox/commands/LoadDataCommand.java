package com.crio.jukebox.commands;

import com.crio.jukebox.services.ISongService;

import java.util.List;

public class LoadDataCommand implements ICommand{

    ISongService songService;

    public LoadDataCommand(ISongService songService){
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            String mes = songService.loadSongs(tokens.get(1));
            System.out.print(mes + "\n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
