package com.unifavipTechTeam.favip.controllers;

import com.unifavipTechTeam.favip.dto.RecoveryPasswordDto;
import com.unifavipTechTeam.favip.dto.UserDto;
import com.unifavipTechTeam.favip.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all-user")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/recovery-password/{id}")
    public ResponseEntity<Void> recoveryPassword(
            @PathVariable Long id,
            @RequestBody RecoveryPasswordDto recoveryPasswordDto
    ) {
        try {
            userService.recoveryPassword(id, recoveryPasswordDto.getNewPassword(), recoveryPasswordDto.getRecoveryKey());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
