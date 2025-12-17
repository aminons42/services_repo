package net.doha.microservice_audit.web;

import jakarta.validation.Valid;
import net.doha.microservice_audit.DTO.AuditDTO;
import net.doha.microservice_audit.DTO.CreateAuditRequest;
import net.doha.microservice_audit.DTO.CreateReponseRequeste;
import net.doha.microservice_audit.DTO.ReponseDTO;
import net.doha.microservice_audit.entities.StatutAudit;
import net.doha.microservice_audit.service.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audits")
public class auditController {
    public auditController(AuditService auditService) {
        this.auditService = auditService;
    }

    private AuditService auditService;

    @PostMapping
    public ResponseEntity<AuditDTO> createAudit(
            @Valid @RequestBody CreateAuditRequest request, Authentication authentication) {
        AuditDTO audit = auditService.createAudit(request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(audit);
    }

    @GetMapping
    public ResponseEntity<List<AuditDTO>> getAllAudits(
            @RequestParam(required = false) StatutAudit statut) {
        if (statut != null) {
            return ResponseEntity.ok(auditService.getAuditsByStatut(statut));
        }
        return ResponseEntity.ok(auditService.getAllAudits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditDTO> getAuditById(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.getAuditById(id));
    }

    @GetMapping("/mes-audits")
    public ResponseEntity<List<AuditDTO>> getMyAudits(Authentication authentication) {
        return ResponseEntity.ok(auditService.getMyAudits(authentication));
    }

    @PutMapping("/{id}/demarrer")
    public ResponseEntity<AuditDTO> demarrerAudit(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.demarrerAudit(id));
    }

    @PutMapping("/{id}/terminer")
    public ResponseEntity<AuditDTO> terminerAudit(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.terminerAudit(id));
    }

    @PostMapping("/{auditId}/reponses")
    public ResponseEntity<ReponseDTO> ajouterReponse(
            @PathVariable Long auditId,
            @Valid @RequestBody CreateReponseRequeste request,
            Authentication authentication) {
        ReponseDTO reponse = auditService.ajouterReponse(auditId, request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }
}
