package com.promptVault.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.promptVault.UserRepository;
import com.promptVault.exception.UserNotFoundException;
import com.promptVault.model.User;

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

    // Get a Single Book
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    // Update an Existing Book
    // @PutMapping("/books/{id}")
    // public Book updateBook(@PathVariable(value = "id") Long bookId, @Valid
    // @RequestBody Book bookDetails)
    // throws BookNotFoundException {
    // Book book = bookRepository.findById(bookId).orElseThrow(() -> new
    // BookNotFoundException(bookId));
    // book.setBook_name(bookDetails.getBook_name());
    // book.setAuthor_name(bookDetails.getAuthor_name());
    // book.setIsbn(bookDetails.getIsbn());
    // Book updatedBook = bookRepository.save(book);
    // return updatedBook;
    // }

    // Delete a Book
    // @DeleteMapping("/books/{id}")
    // public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId)
    // throws BookNotFoundException {
    // Book book = bookRepository.findById(bookId).orElseThrow(() -> new
    // BookNotFoundException(bookId));
    // bookRepository.delete(book);
    // return ResponseEntity.ok().build();
    // }

}