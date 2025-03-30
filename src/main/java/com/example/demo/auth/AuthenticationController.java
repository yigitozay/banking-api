package com.example.demo.auth;

import com.example.demo.entity.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private CustomerRepository customerRepository; // New
    @Autowired
    private PasswordEncoder passwordEncoder; // New

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        System.out.println("LOGIN ATTEMPT: " + request.getEmail());
        String jwt = jwtService.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // Use the parameterized constructor
        Customer customer = new Customer(
                "New User",                      // name
                request.getEmail(),              // email
                passwordEncoder.encode(request.getPassword()), // password
                LocalDateTime.now(),             // createdAt
                null                             // accounts (null for now)
        );

        customerRepository.save(customer);

        String jwt = jwtService.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
    @GetMapping("/test")
    public String test() {
        System.out.println(">> Hit /test route");
        return "It works";
    }
}