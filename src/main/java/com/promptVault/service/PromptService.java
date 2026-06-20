package com.promptVault.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promptVault.model.Prompt;
import com.promptVault.model.User;
import com.promptVault.repository.PromptRepository;

@Service
public class PromptService {

    private final PromptRepository promptRepository;

    public PromptService(PromptRepository promptRepository) {
        this.promptRepository = promptRepository;
    }

    public List<Prompt> getAllPrompts() {
        return promptRepository.findAll();
    }

    public Prompt getPrompt(Long id) {
        return promptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prompt not found"));
    }

    public Prompt savePrompt(Prompt prompt) {
        return promptRepository.save(prompt);
    }

    public void deletePrompt(Long id) {
        promptRepository.deleteById(id);
    }

    public List<Prompt> findAllByUser(User user) {
        return promptRepository.findByOwner(user);
    }

    public List<Prompt> findAllShared() {
        return promptRepository.findBySharedTrue();
    }

    public Prompt findByIdAndUser(Long id, User user) {

        return promptRepository
                .findByIdAndOwner(id, user)
                .orElseThrow(() -> new RuntimeException("Prompt not found"));
    }
}
