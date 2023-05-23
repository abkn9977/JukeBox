package com.crio.jukebox.appConfigs;

import com.crio.jukebox.commands.*;
import com.crio.jukebox.repositories.*;
import com.crio.jukebox.services.*;

public class AppConfig {
    //repositories
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private final ISongRepository songRepository = new SongRepository();
    private final IUserRepository userRepository = new UserRepository();

    //services
    private final IUserService userService = new UserService(userRepository);
    private final ISongService songService = new SongService(songRepository);
    private final IPlaylistService playlistService = new PlaylistService(playlistRepository, userRepository, songRepository);
    private final IJukeBox jukeBoxService = new JukeBox(userRepository, songRepository, playlistRepository);

    //commands
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songService);
    private final ModifyPlaylistCommad modifyPlaylistCommad = new ModifyPlaylistCommad(playlistService);
    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(jukeBoxService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(jukeBoxService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){

        commandInvoker.register("LOAD-DATA", loadDataCommand);
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommad);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);

        return commandInvoker;
    }
}
