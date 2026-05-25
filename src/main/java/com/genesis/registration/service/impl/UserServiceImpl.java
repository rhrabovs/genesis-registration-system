package com.genesis.registration.service.impl;

import com.genesis.registration.model.User;
import com.genesis.registration.repository.UserRepository;
import com.genesis.registration.service.UserService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final List<String> validPersonId;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        this.validPersonId = Files.readAllLines(
                Path.of("src/main/resources/dataPersonId.txt")
        );
    }

    @Override
    public User createUser(User user){

        if (user.getPersonId().length() != 12){
            throw new IllegalArgumentException("personId musi mit delku 12 znaku");
        }

        if (!validPersonId.contains(user.getPersonId())){
            throw new IllegalArgumentException("Neplatna autorizace personId");
        }

        if (userRepository.existingByPersonId(user.getPersonId())){
            throw new IllegalArgumentException("personId jiz v systemu existuje")
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer id, User updateUser){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateUser.getName());
                    user.setSurname(updateUser.getSurname());
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    @Override
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
