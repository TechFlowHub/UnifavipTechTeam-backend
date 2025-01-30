package com.unifavipTechTeam.favip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unifavipTechTeam.favip.entity.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    
}
