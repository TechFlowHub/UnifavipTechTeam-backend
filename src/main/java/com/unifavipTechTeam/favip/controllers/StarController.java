package com.unifavipTechTeam.favip.controllers;

import com.unifavipTechTeam.favip.entity.PersonalData;
import com.unifavipTechTeam.favip.entity.Star;
import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.service.PersonalDataService;
import com.unifavipTechTeam.favip.service.StarService;
import com.unifavipTechTeam.favip.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/star")
public class StarController {

    private final StarService starService;
    private final UserService userService;
    private final PersonalDataService personalDataService;

    public StarController(StarService starService, UserService userService, PersonalDataService personalDataService) {
        this.starService = starService;
        this.userService = userService;
        this.personalDataService = personalDataService;
    }

    @PostMapping("/give")
    public ResponseEntity<String> giveStar(@RequestParam Long giverId, @RequestParam Long receiverId) {
        User giver = userService.findById(giverId);
        PersonalData receiver = personalDataService.findById(receiverId);

        if (giver == null || receiver == null) {
            return ResponseEntity.badRequest().body("Usuário ou Personal Data não encontrado.");
        }

        Star star = new Star();
        star.setGiver(giver);
        star.setReceiver(receiver);
        starService.save(star);

        return ResponseEntity.ok("Estrela enviada com sucesso!");
    }

    @GetMapping("/get/{receiverId}")
    public ResponseEntity<Integer> countStars(@PathVariable Long receiverId) {
        int starCount = starService.countStars(receiverId);
        return ResponseEntity.ok(starCount);
    }
}
