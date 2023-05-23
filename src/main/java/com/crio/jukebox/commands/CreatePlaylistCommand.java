package com.crio.jukebox.commands;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

import java.util.ArrayList;
import java.util.List;

public class CreatePlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService ps){
        this.playlistService = ps;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            List<String> songIds = new ArrayList<>();
            for(int i = 3; i < tokens.size(); i ++)
                songIds.add(tokens.get(i));

            Playlist playlist = playlistService.createPlaylist(tokens.get(1), tokens.get(2), songIds);
            System.out.print("Playlist ID - " + playlist.getId() + "\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}