package com.unifavipTechTeam.favip.service;

import com.unifavipTechTeam.favip.dto.UserDto;
import com.unifavipTechTeam.favip.entity.RecoveryCode;
import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.repositories.RecoveryCodeRepository;
import com.unifavipTechTeam.favip.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RecoveryCodeRepository recoveryCodeRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RecoveryCodeRepository recoveryCodeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.recoveryCodeRepository = recoveryCodeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToUserDto);
    }

    private UserDto convertToUserDto(User user) {
        return new UserDto(
                String.valueOf(user.getId()),
                user.getEmail()
        );
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public void recoveryPassword(Long id, String newPassword, String recoveryKey) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User not found with ID: " + id)
        );

        RecoveryCode recoveryCode = recoveryCodeRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Recovery key not found for this user."));
        try {
            if (!recoveryCode.isValid() || !CryptoService.decrypt(recoveryCode.getCode()).equals(recoveryKey)) {
                throw new IllegalArgumentException("Invalid recovery key.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while decrypting the recovery key.", e);
        }
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);

        recoveryCode.setValid(false);
        recoveryCodeRepository.save(recoveryCode);
    }
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }
}
