package com.unifavipTechTeam.favip.repositories;

import com.unifavipTechTeam.favip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}
