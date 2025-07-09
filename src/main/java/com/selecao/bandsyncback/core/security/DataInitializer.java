package com.selecao.bandsyncback.core.security;

import com.selecao.bandsyncback.core.user.User;
import com.selecao.bandsyncback.core.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner createTestUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String email = "test@bandsync.fr";

            if (userRepository.findByEmail(email) == null) {
                User user = new User();
                user.setName("test");
                user.setEmail(email);
                user.setPassword(passwordEncoder.encode("bandsync"));
                user.setInstrument("Piano");
                user.setRole("USER");

                userRepository.save(user);
                log.info("User {} inserted", email);
            } else {
                log.info("There is already a user with the email {}", email);
            }
        };
    }
}