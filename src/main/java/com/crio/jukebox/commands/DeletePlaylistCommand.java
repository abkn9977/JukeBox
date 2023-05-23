package com.crio.jukebox.commands;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

import java.util.List;

public class DeletePlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService ps){
        this.playlistService = ps;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            Playlist playlist = playlistService.deletePlaylist(tokens.get(1), tokens.get(2));
            System.out.print("Delete Successful\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
