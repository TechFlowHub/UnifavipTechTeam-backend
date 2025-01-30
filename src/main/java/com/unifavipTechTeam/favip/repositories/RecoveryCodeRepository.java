package com.unifavipTechTeam.favip.repositories;

import com.unifavipTechTeam.favip.entity.RecoveryCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecoveryCodeRepository extends JpaRepository<RecoveryCode, Long> {
    Optional<RecoveryCode> findByEmail(String email);
}