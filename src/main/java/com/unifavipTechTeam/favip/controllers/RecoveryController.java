package com.unifavipTechTeam.favip.controllers;

import com.unifavipTechTeam.favip.dto.RecoveryVerificationDto;
import com.unifavipTechTeam.favip.entity.RecoveryCode;
import com.unifavipTechTeam.favip.repositories.RecoveryCodeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("recovery")
public class RecoveryController {

    private final RecoveryCodeRepository recoveryCodeRepository;

    public RecoveryController(RecoveryCodeRepository recoveryCodeRepository) {
        this.recoveryCodeRepository = recoveryCodeRepository;
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyRecoveryCode(@RequestBody RecoveryVerificationDto verificationDto) {
        Optional<RecoveryCode> recoveryCodeOpt = recoveryCodeRepository.findByEmail(verificationDto.email());

        if (recoveryCodeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Código de recuperação não encontrado.");
        }

        RecoveryCode recoveryCode = recoveryCodeOpt.get();

        if (!recoveryCode.getCode().equals(verificationDto.code())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código de recuperação inválido.");
        }

        return ResponseEntity.ok("Código de recuperação válido.");
    }
}