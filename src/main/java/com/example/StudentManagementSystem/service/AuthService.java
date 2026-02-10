package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.*;
import com.example.StudentManagementSystem.model.UserModel;
import com.example.StudentManagementSystem.repository.UserRepository;
import com.example.StudentManagementSystem.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public TokenResponseDto login(LoginRequestDto dto) {

        UserModel user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new TokenResponseDto(token);
    }

    public TokenResponseDto register(RegisterRequestDto dto) {

        // Check if user already exists
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Create new user
        UserModel newUser = new UserModel();
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword()); // Note: In production, hash the password!

        repository.save(newUser);

        // Generate token for the new user
        String token = jwtUtil.generateToken(newUser.getEmail());

        return new TokenResponseDto(token);
    }
}
