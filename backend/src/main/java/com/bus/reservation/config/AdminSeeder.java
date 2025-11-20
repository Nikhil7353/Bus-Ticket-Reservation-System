package com.bus.reservation.config;

import com.bus.reservation.entity.User;
import com.bus.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@ConditionalOnProperty(name = "app.admin.seed", havingValue = "true", matchIfMissing = true)
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    private final String email;
    private final String password;
    private final String name;

    public AdminSeeder(
            UserRepository userRepository,
            @Value("${app.admin.email:admin@bus.local}") String email,
            @Value("${app.admin.password:admin123}") String password,
            @Value("${app.admin.name:Admin}") String name
    ) {
        this.userRepository = userRepository;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Override
    public void run(String... args) {
        userRepository.findByEmail(email).ifPresentOrElse(
                user -> { },
                () -> {
                    User admin = new User();
                    admin.setName(name);
                    admin.setEmail(email);
                    admin.setPhone("0000000000");
                    admin.setPassword(password);
                    admin.setRole("ADMIN");
                    userRepository.save(admin);
                }
        );
    }
}

