package com.br.medtech.controller;

import com.br.medtech.model.Clinic;
import com.br.medtech.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clinic>> findClinicById(@PathVariable Long id) {
        return clinicService.findClinicById(id);
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> findAllClinics() {
        return clinicService.findAllClinics();
    }

    @PostMapping
    public ResponseEntity<Clinic> add(@RequestBody Clinic clinic) {
        return clinicService.add(clinic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinic> update(@PathVariable Long id, @RequestBody Clinic clinic) {
        return clinicService.update(id, clinic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Clinic> delete(@PathVariable Long id) {
        return clinicService.delete(id);
    }

}
