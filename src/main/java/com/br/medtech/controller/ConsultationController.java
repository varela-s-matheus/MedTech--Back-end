package com.br.medtech.controller;

import com.br.medtech.model.Consultation;
import com.br.medtech.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/appointment")
public class ConsultationController {
    
    @Autowired
    private ConsultationService consultationService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Consultation>> findConsultationById(@PathVariable Long id) {
        return consultationService.findConsultationById(id);
    }

    @GetMapping
    public ResponseEntity<List<Consultation>> findAllConsultations() {
        return consultationService.findAllConsultations();
    }

    @PostMapping
    public ResponseEntity<Consultation> add(@RequestBody Consultation Consultation) {
        return consultationService.add(Consultation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultation> update(@PathVariable Long id, @RequestBody Consultation Consultation) {
        return consultationService.update(id, Consultation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Consultation> delete(@PathVariable Long id) {
        return consultationService.delete(id);
    }
}
