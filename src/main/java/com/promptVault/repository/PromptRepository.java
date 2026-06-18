package com.promptVault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.promptVault.model.Prompt;

public interface PromptRepository extends JpaRepository<Prompt, Long> {
}
