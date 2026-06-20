package com.promptVault.config;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.promptVault.model.User;
import com.promptVault.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createAdminUser(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            Optional<User> existingAdmin = userRepository.findByUsername("admin");

            if (existingAdmin.isEmpty()) {

                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@promptvault.local");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setEnabled(true);
                admin.setRole(User.Role.ADMIN.name());

                userRepository.save(admin);

                System.out.println("Admin user created");
            }
        };
    }
}
