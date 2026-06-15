package com.promptVault.exception;

public class PromptNotFoundException extends Exception {

    public PromptNotFoundException(long promptId) {
        super(String.format("Prompt has not been found with id : '%s'", promptId));
    }
}
