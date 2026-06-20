package com.promptVault.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "prompt_submissions")
public class PromptSubmission {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prompt_id")
    private Prompt prompt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime submittedAt;

    @Column(columnDefinition = "TEXT")
    private String responseText;

    @PrePersist
    public void onCreate() {
        submittedAt = LocalDateTime.now();
    }

    public PromptSubmission() {
    }

    public PromptSubmission(
            Prompt prompt,
            User user,
            LocalDateTime submittedAt,
            String responseText) {

        this.prompt = prompt;
        this.user = user;
        this.submittedAt = submittedAt;
        this.responseText = responseText;
    }

    // getters/setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
}