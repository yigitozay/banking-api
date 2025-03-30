package com.example.demo.config;

import com.example.demo.entity.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.findByEmail("test@example.com").isEmpty()) {
            Customer defaultUser = new Customer(
                    "Test User",
                    "test@example.com",
                    passwordEncoder.encode("password123"),
                    LocalDateTime.now(),
                    Collections.emptyList()
            );
            customerRepository.save(defaultUser);
            System.out.println("Default user created: test@example.com");
        } else {
            System.out.println("Default user already exists: test@example.com");
        }
    }
}