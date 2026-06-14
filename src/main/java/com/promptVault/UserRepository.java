package com.promptVault;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.promptVault.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
