package com.unifavipTechTeam.favip.controllers;

import com.unifavipTechTeam.favip.dto.RecoveryVerificationDto;
import com.unifavipTechTeam.favip.entity.RecoveryCode;
import com.unifavipTechTeam.favip.repositories.RecoveryCodeRepository;
import com.unifavipTechTeam.favip.service.CryptoService;
import com.unifavipTechTeam.favip.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recovery")
public class    RecoveryController {

    private final RecoveryCodeRepository recoveryCodeRepository;
    private final UserService userService;

    public RecoveryController(RecoveryCodeRepository recoveryCodeRepository, UserService userService) {
        this.recoveryCodeRepository = recoveryCodeRepository;
        this.userService = userService;
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyRecoveryCode(@RequestBody RecoveryVerificationDto verificationDto) {
        try {
            Optional<RecoveryCode> recoveryCodeOpt = recoveryCodeRepository.findByEmail(verificationDto.email());

            if (recoveryCodeOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recovery code not found.");
            }

            RecoveryCode recoveryCode = recoveryCodeOpt.get();
            String decryptedCode = CryptoService.decrypt(recoveryCode.getCode());

            if (!decryptedCode.equals(verificationDto.code())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid recovery code.");
            }

            recoveryCode.setValid(true);
            recoveryCodeRepository.save(recoveryCode);

            return ResponseEntity.ok("Recovery code verified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the verification.");
        }
    }
    @GetMapping("/get-valid/{email}")
    public ResponseEntity<Boolean> getValidRecoveryCode(@PathVariable String email) {
        Optional<RecoveryCode> recoveryCodeOpt = recoveryCodeRepository.findByEmail(email);

        if (recoveryCodeOpt.isPresent()) {
            boolean isValid = recoveryCodeOpt.get().isValid();
            return ResponseEntity.ok(isValid);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}
