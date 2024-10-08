package com.br.medtech.controller;

import com.br.medtech.model.Administrator;
import com.br.medtech.model.Clinic;
import com.br.medtech.service.ClinicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clinic")
@SecurityRequirement(name = "bearer-key")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clinic>> findClinicById(@PathVariable int id) {
        return clinicService.findClinicById(id);
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> findAllClinics() {
        return clinicService.findAllClinics();
    }

    @PostMapping("/{idAdm}")
    public ResponseEntity<Clinic> add(@RequestBody Clinic clinic, @PathVariable int idAdm) {
        return clinicService.add(idAdm, clinic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinic> update(@PathVariable int id, @RequestBody Clinic clinic) {
        return clinicService.update(id, clinic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Clinic> delete(@PathVariable int id) {
        return clinicService.delete(id);
    }

}
