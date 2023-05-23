package com.crio.jukebox.commands;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;

import java.util.List;

public class CreateUserCommand implements ICommand{
    private final IUserService userService;

    public CreateUserCommand(IUserService us){
        this.userService = us;
    }
    @Override
    public void execute(List<String> tokens) {
        User user = userService.create(tokens.get(1));
        System.out.print(user.getId() + " " + user.getName() + "\n");
    }
}
