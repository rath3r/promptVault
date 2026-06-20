package com.promptVault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "flagged_keywords")
public class FlaggedKeyword {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String keyword;

    public FlaggedKeyword() {
    }

    public FlaggedKeyword(Long id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}