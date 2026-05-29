
package com.genesis.registration.service.impl;

import com.genesis.registration.dto.*;
        import com.genesis.registration.model.User;
import com.genesis.registration.repository.UserRepository;
import com.genesis.registration.service.UserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final List<String> validPersonIds;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        try {
            this.validPersonIds = Files.readAllLines(
                    Path.of("src/main/resources/dataPersonId.txt"),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            throw new RuntimeException("Nepodarilo se nacist dataPersonId.txt", e);
        }
    }

    @Override
    public UserDetailDTO createUser(CreateUserDTO dto) {

        // kontrola platnosti autorizace
        if (!validPersonIds.contains(dto.getPersonId())) {
            throw new IllegalArgumentException("Neplatna autorizace personId");
        }

        // kontrola existence personal ID v systemu, tzn. ze personal ID je jiz prideleno
        if (userRepository.existsByPersonId(dto.getPersonId())) {
            throw new IllegalArgumentException("personId jiz v systemu existuje");
        }

        if (dto.getPersonId().length() != 12) {
            throw new IllegalArgumentException("personId musi mit delku 12 znaku");
        }

        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (userRepository.existsByUuid(uuid));

        User user = new User(dto.getName(), dto.getSurname(), dto.getPersonId(), uuid);
        User saved = userRepository.save(user);

        return new UserDetailDTO(
                saved.getId(),
                saved.getName(),
                saved.getSurname(),
                saved.getPersonId(),
                saved.getUuid()
        );
    }

    @Override
    public UserShortDTO getUserByIdShort(Long id) {
        return userRepository.findById(id)
                .map(u -> new UserShortDTO(u.getId(), u.getName(), u.getSurname()))
                .orElse(null);
    }

    @Override
    public UserDetailDTO getUserByIdDetail(Long id) {
        return userRepository.findById(id)
                .map(u -> new UserDetailDTO(
                        u.getId(),
                        u.getName(),
                        u.getSurname(),
                        u.getPersonId(),
                        u.getUuid()
                ))
                .orElse(null);
    }

    @Override
    public List<UserShortDTO> getAllUsersShort() {
        return userRepository.findAll().stream()
                .map(u -> new UserShortDTO(u.getId(), u.getName(), u.getSurname()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDetailDTO> getAllUsersDetail() {
        return userRepository.findAll().stream()
                .map(u -> new UserDetailDTO(
                        u.getId(),
                        u.getName(),
                        u.getSurname(),
                        u.getPersonId(),
                        u.getUuid()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public UserShortDTO updateUser(UpdateUserDTO dto) {
        return userRepository.findById(dto.getId())
                .map(u -> {
                    u.setName(dto.getName());
                    u.setSurname(dto.getSurname());
                    User saved = userRepository.save(u);
                    return new UserShortDTO(saved.getId(), saved.getName(), saved.getSurname());
                })
                .orElse(null);
    }


    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Uzivatel nebyl nalezen");
        }

        userRepository.deleteById(id);
    }

}
