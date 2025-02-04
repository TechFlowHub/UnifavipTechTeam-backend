package com.unifavipTechTeam.favip.service;

import com.unifavipTechTeam.favip.dto.UserDto;
import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
