
package com.genesis.registration.controller;

import com.genesis.registration.dto.*;
        import com.genesis.registration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** zalozeni noveho uzivatele, POST api/v1/users */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO dto) {
        try {
            UserDetailDTO created = userService.createUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /** informace o uzivateli, GET api/v1/users/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id,
            @RequestParam(name = "detail", required = false, defaultValue = "false") boolean detail
    ) {
        if (detail) {
            UserDetailDTO user = userService.getUserByIdDetail(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uzivatel nebyl nalezen");
            }
            return ResponseEntity.ok(user);
        } else {
            UserShortDTO user = userService.getUserByIdShort(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uzivatel nebyl nalezen");
            }
            return ResponseEntity.ok(user);
        }
    }

    /** informace o vsech uzivatelych */
    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @RequestParam(name = "detail", required = false, defaultValue = "false") boolean detail
    ) {
        if (detail) {
            List<UserDetailDTO> users = userService.getAllUsersDetail();
            return ResponseEntity.ok(users);
        } else {
            List<UserShortDTO> users = userService.getAllUsersShort();
            return ResponseEntity.ok(users);
        }
    }

    /** aktualizace udaju uzivatele */
    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDTO dto) {
        UserShortDTO updated = userService.updateUser(dto);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uzivatel nebyl nalezen");
        }

        return ResponseEntity.ok(updated);
    }

    /** smazani uzivatele */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
