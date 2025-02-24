package com.unifavipTechTeam.favip.controllers;

import com.unifavipTechTeam.favip.dto.StarRequestDto;
import com.unifavipTechTeam.favip.entity.PersonalData;
import com.unifavipTechTeam.favip.entity.Star;
import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.service.PersonalDataService;
import com.unifavipTechTeam.favip.service.StarService;
import com.unifavipTechTeam.favip.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> giveStar(@RequestBody @Valid StarRequestDto starRequestDto) {
        User giver = userService.findById(starRequestDto.giverId());
        PersonalData receiver = personalDataService.findById(starRequestDto.receiverId());

        if (giver == null || receiver == null) {
            return ResponseEntity.badRequest().body("Usuário ou Personal Data não encontrado.");
        }

        Star star = new Star();
        star.setGiver(giver);
        star.setReceiver(receiver);
        starService.save(star);

        return ResponseEntity.ok("Estrela enviada com sucesso!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStar(@PathVariable Long id) {
        Star star = starService.findById(id);
        if (star == null) {
            return ResponseEntity.badRequest().body("Estrela não encontrada.");
        }

        starService.delete(id);
        return ResponseEntity.ok("Estrela deletada com sucesso!");
    }
}
