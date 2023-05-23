package com.crio.jukebox.commands;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

import java.util.ArrayList;
import java.util.List;

public class ModifyPlaylistCommad implements ICommand{
    private final IPlaylistService playlistService;

    public ModifyPlaylistCommad(IPlaylistService ps){
        this.playlistService = ps;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            List<String> songIds = new ArrayList<>();
            for(int i = 4; i < tokens.size(); i ++)
                songIds.add(tokens.get(i));

            Playlist playlist = playlistService.modifyPlaylist(tokens.get(1), tokens.get(2), tokens.get(3), songIds);
            System.out.print(playlist);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
