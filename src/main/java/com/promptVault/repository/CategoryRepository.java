package com.promptVault.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promptVault.model.Category;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {
}