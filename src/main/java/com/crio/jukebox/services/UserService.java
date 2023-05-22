package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;

import java.util.List;

public class UserService implements IUserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User create(String name) {
        if(name.isEmpty())
            return null;
        return userRepository.save(new User(name));
    }

}