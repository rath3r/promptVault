package com.promptVault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promptVault.model.PromptSubmission;
import com.promptVault.model.User;

public interface PromptSubmissionRepository
        extends JpaRepository<PromptSubmission, Long> {

    List<PromptSubmission> findByUserOrderBySubmittedAtDesc(User user);
}