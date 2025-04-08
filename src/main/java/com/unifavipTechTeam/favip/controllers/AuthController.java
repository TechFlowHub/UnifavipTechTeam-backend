package com.unifavipTechTeam.favip.controllers;

import com.unifavipTechTeam.favip.dto.LoginRequestDto;
import com.unifavipTechTeam.favip.dto.RegisterRequestDto;
import com.unifavipTechTeam.favip.dto.ResponseDto;
import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.repositories.UserRepository;
import com.unifavipTechTeam.favip.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto body){
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDto(user.getEmail(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout realizado com sucesso.");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated   RegisterRequestDto body) {
        Optional<User> existingUser = this.userRepository.findByEmail(body.email());

        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setRole(body.role());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDto(newUser.getEmail(), token));
        }

        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/register-admin")
    public ResponseEntity registerAdmin(@RequestBody RegisterRequestDto body) {
        Optional<User> existingUser = this.userRepository.findByEmail(body.email());

        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setRole(body.role());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDto(newUser.getEmail(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestBody Map<String, String> body) {
        String token = body.get("token");

        String email = tokenService.validateToken(token);

        Map<String, Object> response = new HashMap<>();
        if (email != null) {
            response.put("valid", true);
            response.put("user", email);
            return ResponseEntity.ok(response);
        } else {
            response.put("valid", false);
            response.put("message", "Token inválido ou expirado.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}