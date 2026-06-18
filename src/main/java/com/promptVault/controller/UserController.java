package com.promptVault.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.promptVault.exception.UserNotFoundException;
import com.promptVault.model.User;
import com.promptVault.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    // Get All Users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    @PostMapping("/users")
    public User newUser(@Valid @RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Get a Single User
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    // Update an Existing User
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
            throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setEnabled(userDetails.getEnabled());

        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    // enable a user with an id
    @PutMapping("/users/{id}/enable")
    public User enableUser(@PathVariable Long id)
            throws UserNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setEnabled(true);

        return userRepository.save(user);
    }

    @PutMapping("/users/{id}/disable")
    public User disableUser(@PathVariable Long id)
            throws UserNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setEnabled(false);

        return userRepository.save(user);
    }

    // Delete a User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId)
            throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}