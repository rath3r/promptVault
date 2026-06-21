package com.promptVault.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "prompts")
public class Prompt {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String promptText;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private Boolean shared;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Boolean flagged = false;

    private String flaggedKeyword;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Prompt() {
        super();
    }

    public Prompt(
            Long id,
            String title,
            String promptText,
            User owner,
            Boolean shared,
            Category category,
            Boolean flagged,
            String flaggedKeyword,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.promptText = promptText;
        this.owner = owner;
        this.shared = shared;
        this.category = category;
        this.flagged = flagged;
        this.flaggedKeyword = flaggedKeyword;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    public String getFlaggedKeyword() {
        return flaggedKeyword;
    }

    public void setFlaggedKeyword(String flaggedKeyword) {
        this.flaggedKeyword = flaggedKeyword;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
