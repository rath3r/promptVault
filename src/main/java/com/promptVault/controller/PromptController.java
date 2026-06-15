package com.promptVault.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.promptVault.PromptRepository;
import com.promptVault.exception.PromptNotFoundException;
import com.promptVault.model.Prompt;

import java.util.List;

@RestController
public class PromptController {
    @Autowired
    PromptRepository promptRepository;

    // Get All Prompts
    @GetMapping("/prompts")
    public List<Prompt> getAllPrompts() {
        return promptRepository.findAll();
    }

    // Create a new prompt
    @PostMapping("/prompts")
    public Prompt newPrompt(@Valid @RequestBody Prompt newPrompt) {
        return promptRepository.save(newPrompt);
    }

    // Get a Single Book
    @GetMapping("/prompts/{id}")
    public Prompt getPromptById(@PathVariable(value = "id") Long promptId) throws PromptNotFoundException {
        return promptRepository.findById(promptId).orElseThrow(() -> new PromptNotFoundException(promptId));
    }

    // Update an Existing Book
    @PutMapping("/prompts/{id}")
    public Prompt updatePrompt(@PathVariable(value = "id") Long promptId, @Valid @RequestBody Prompt promptDetails)
            throws PromptNotFoundException {
        Prompt prompt = promptRepository.findById(promptId).orElseThrow(() -> new PromptNotFoundException(promptId));
        prompt.setTitle(promptDetails.getTitle());
        prompt.setPromptText(promptDetails.getPromptText());
        // prompt.setIsbn(userDetails.getIsbn());
        Prompt updatedPrompt = promptRepository.save(prompt);
        return updatedPrompt;
    }

    // Delete a Book
    @DeleteMapping("/prompts/{id}")
    public ResponseEntity<?> deletePrompt(@PathVariable(value = "id") Long promptId)
            throws PromptNotFoundException {
        Prompt prompt = promptRepository.findById(promptId).orElseThrow(() -> new PromptNotFoundException(promptId));
        promptRepository.delete(prompt);
        return ResponseEntity.ok().build();
    }

}