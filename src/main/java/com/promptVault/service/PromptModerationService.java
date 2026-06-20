package com.promptVault.service;

import org.springframework.stereotype.Service;

import com.promptVault.repository.FlaggedKeywordRepository;

@Service
public class PromptModerationService {

    private final FlaggedKeywordRepository keywordRepository;

    public PromptModerationService(
            FlaggedKeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public boolean containsFlaggedKeywords(String text) {

        String lowerText = text.toLowerCase();

        return keywordRepository.findAll().stream()
                .anyMatch(keyword -> lowerText.contains(
                        keyword.getKeyword().toLowerCase()));
    }
}
