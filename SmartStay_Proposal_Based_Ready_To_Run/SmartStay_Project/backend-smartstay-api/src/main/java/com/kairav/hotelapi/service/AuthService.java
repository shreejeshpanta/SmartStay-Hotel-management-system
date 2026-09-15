package com.kairav.hotelapi.service;

import com.kairav.hotelapi.dto.LoginRequest;
import com.kairav.hotelapi.dto.LoginResponse;
import com.kairav.hotelapi.entity.Status;
import com.kairav.hotelapi.entity.User;
import com.kairav.hotelapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
    public AuthService(UserRepository userRepository) {
        this.userRepository =userRepository;
    }
    public LoginResponse login(LoginRequest request) {
        User user =userRepository.findByUsername(request.getUsername()).orElseThrow(() ->new RuntimeException("Invalid username or password"));
        // Proposal requirement: secure login with encrypted password mechanism (BCrypt).
        if (!encoder.matches(request.getPassword(),user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        if (user.getStatus() !=Status.ACTIVE) {
            throw new RuntimeException("User is inactive");
        }
        return new LoginResponse(user.getId(),user.getFullName(),user.getUsername(),user.getRole(),"Login successful");
    }
}
