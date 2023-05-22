package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("UserService Test Unit")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    IUserRepository userRepositoryMock;

    @Test
    @DisplayName("create user method should return null")
    public void create_shouldThrowError() {
        //act
        User actual = userService.create("");
        //assert
        assertNull(actual);
    }
}
