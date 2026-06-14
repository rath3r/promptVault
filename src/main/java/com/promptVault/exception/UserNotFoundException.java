package com.promptVault.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(long userId) {
        super(String.format("User has not been found with id : '%s'", userId));
    }
}
