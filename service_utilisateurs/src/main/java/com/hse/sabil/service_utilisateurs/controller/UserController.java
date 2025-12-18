package com.hse.sabil.service_utilisateurs.controller;

import com.hse.sabil.service_utilisateurs.dto.UserDTO;
import com.hse.sabil.service_utilisateurs.model.Utilisateur;
import com.hse.sabil.service_utilisateurs.repo_jpa.Utilisateur_repo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Utilisateur_repo utilisateurRepo;

    public UserController(Utilisateur_repo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Utilisateur user = utilisateurRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        return ResponseEntity.ok(dto);
    }
}