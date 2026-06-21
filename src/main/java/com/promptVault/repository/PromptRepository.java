package com.promptVault.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.promptVault.model.Prompt;
import com.promptVault.model.User;

public interface PromptRepository extends JpaRepository<Prompt, Long> {
    List<Prompt> findByFlaggedTrue();

    List<Prompt> findByOwner(User owner);

    List<Prompt> findBySharedTrue();

    // List<Prompt> findByIdAndUser(Long id, User owner);

    Optional<Prompt> findByIdAndOwner(Long id, User owner);
}
