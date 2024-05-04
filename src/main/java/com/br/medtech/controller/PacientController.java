package com.br.medtech.controller;

import com.br.medtech.model.Patient;
import com.br.medtech.service.PatienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PacientController {
    @Autowired
    private PatienteService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> findPatientById(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAllPatients() {
        return patientService.findAllPatients();
    }

    @PostMapping
    public ResponseEntity<Patient> add(@RequestBody Patient patient) {
        return patientService.add(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.update(id, patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable Long id) {
        return patientService.delete(id);
    }

}
