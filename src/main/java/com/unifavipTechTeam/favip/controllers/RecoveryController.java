package com.unifavipTechTeam.favip.controllers;

import com.unifavipTechTeam.favip.dto.RecoveryVerificationDto;
import com.unifavipTechTeam.favip.entity.RecoveryCode;
import com.unifavipTechTeam.favip.repositories.RecoveryCodeRepository;
import com.unifavipTechTeam.favip.service.CryptoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recovery")
public class RecoveryController {

    private final RecoveryCodeRepository recoveryCodeRepository;

    public RecoveryController(RecoveryCodeRepository recoveryCodeRepository) {
        this.recoveryCodeRepository = recoveryCodeRepository;
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyRecoveryCode(@RequestBody RecoveryVerificationDto verificationDto) {
        try {
            Optional<RecoveryCode> recoveryCodeOpt = recoveryCodeRepository.findByEmail(verificationDto.email());

            if (recoveryCodeOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Verification code not found.");
            }

            RecoveryCode recoveryCode = recoveryCodeOpt.get();
            String decryptedCode = CryptoService.decrypt(recoveryCode.getCode());

            if (!decryptedCode.equals(verificationDto.code())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid recuperation code.");
            }

            return ResponseEntity.ok("Valid recuperation code.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the verification.");
        }
    }
}