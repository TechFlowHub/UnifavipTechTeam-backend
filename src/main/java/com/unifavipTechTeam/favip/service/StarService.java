package com.unifavipTechTeam.favip.service;

import com.unifavipTechTeam.favip.entity.Star;
import com.unifavipTechTeam.favip.repository.StarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarService {

    private final StarRepository starRepository;

    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }
    public Star findById(Long id) {
        return starRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        starRepository.deleteById(id);
    }

    public Star save(Star star) {
        return starRepository.save(star);
    }

    public int countStars(Long receiverId) {
        return starRepository.countByReceiverId(receiverId);
    }

    public List<Star> findStarsByGiver(Long giverId) {
        return starRepository.findByGiverId(giverId);
    }

    public List<Star> findStarsByReceiver(Long receiverId) {
        return starRepository.findByReceiverId(receiverId);
    }
}
