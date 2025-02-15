package com.unifavipTechTeam.favip.repository;

import com.unifavipTechTeam.favip.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    int countByReceiverId(Long receiverId);

    List<Star> findByGiverId(Long giverId);

    List<Star> findByReceiverId(Long receiverId);
}
