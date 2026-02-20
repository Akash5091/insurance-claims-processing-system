package com.xyz.insurance;

import com.xyz.insurance.user.User;
import com.xyz.insurance.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userRepository.findByUsername("agent1")
            .orElseGet(() -> {
                User u = new User();
                u.setUsername("agent1");
                u.setPasswordHash(passwordEncoder.encode("password123"));
                u.setRole("AGENT");
                u.setActive(true);
                return userRepository.save(u);
            });
        
        userRepository.findByUsername("admin1")
            .orElseGet(() -> {
                User u = new User();
                u.setUsername("admin1");
                u.setPasswordHash(passwordEncoder.encode("admin123"));
                u.setRole("ADMIN");
                u.setActive(true);
                return userRepository.save(u);
            });
    }
}
