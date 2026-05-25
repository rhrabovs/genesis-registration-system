package com.genesis.registration.controller;

import com.genesis.registration.model.User;
import com.genesis.registration.service.UserService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            User saved = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);

        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Uzivatel s id="+id+" nebyl nalezen");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public RequestEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
