package com.promptVault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.promptVault.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
