package com.genesis.registration.service;

import com.genesis.registration.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUserById(Integer id);
    List<User> getAllUsers();
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
}
