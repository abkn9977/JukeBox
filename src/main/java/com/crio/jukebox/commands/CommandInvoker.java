package com.crio.jukebox.commands;

import com.crio.jukebox.exceptions.NoSuchCommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private static final Map<String, ICommand> commands = new HashMap<String, ICommand>();

    //register command to the map
    public void register(String commandName, ICommand command){
        commands.put(commandName, command);
    }

    //get command
    public ICommand get(String commandName){
        return commands.get(commandName);
    }

    //execute command
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        ICommand command = get(commandName);

        if(command == null){
            throw new NoSuchCommandException();
        }

        command.execute(tokens);
    }
}