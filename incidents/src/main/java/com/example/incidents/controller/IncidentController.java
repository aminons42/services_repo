package com.example.incidents.controller;

import com.example.incidents.dto.IncidentRequestDto;
import com.example.incidents.dto.IncidentResponseDto;
import com.example.incidents.service.IncidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/incidents")
@CrossOrigin("*")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // --- 1. CREATE ---
    @PostMapping
    public ResponseEntity<IncidentResponseDto> createIncident(@RequestBody IncidentRequestDto incident) {
        IncidentResponseDto response = incidentService.create(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // --- 2. GET ALL ---
    @GetMapping
    public ResponseEntity<List<IncidentResponseDto>> getAllIncidents() {
        List<IncidentResponseDto> responseDtos = incidentService.getAll();
        return ResponseEntity.ok(responseDtos); // Raccourci pour status(HttpStatus.OK).body(...)
    }

    // --- 3. GET BY ID (Nouveau) ---
    @GetMapping("/{id}") // L'URL sera /api/incidents/5
    public ResponseEntity<IncidentResponseDto> getIncidentById(@PathVariable Long id) {
        // @PathVariable capture le "5" de l'URL et le met dans "id"
        IncidentResponseDto response = incidentService.getById(id);
        return ResponseEntity.ok(response);
    }

    // --- 4. UPDATE (Nouveau) ---
    @PutMapping("/{id}")
    public ResponseEntity<IncidentResponseDto> updateIncident(
            @PathVariable Long id,
            @RequestBody IncidentRequestDto request) {

        // On a besoin de l'ID (URL) pour savoir QUOI modifier
        // Et du Body (JSON) pour savoir COMMENT modifier
        IncidentResponseDto response = incidentService.update(id, request);
        return ResponseEntity.ok(response);
    }

    // --- 5. DELETE (Nouveau) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.delete(id);
        // On renvoie souvent "204 No Content" quand on supprime
        return ResponseEntity.noContent().build();
    }
}