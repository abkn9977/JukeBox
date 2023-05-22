package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.InvalidOperationException;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User create(String name);
}