package com.promptVault.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promptVault.model.FlaggedKeyword;

public interface FlaggedKeywordRepository
        extends JpaRepository<FlaggedKeyword, Long> {
}
