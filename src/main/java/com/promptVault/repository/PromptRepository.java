package com.promptVault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.promptVault.model.Prompt;

public interface PromptRepository extends JpaRepository<Prompt, Long> {
    List<Prompt> findByFlaggedTrue();
}
