package com.br.medtech.controller;

import com.br.medtech.model.Administrator;
import com.br.medtech.service.AdministratorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/administrator")
@SecurityRequirement(name = "bearer-key")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Administrator>> findAdministratorById(@PathVariable Integer id) {
        return administratorService.findAdministratorById(id);
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<Administrator>> findAllAdministratorsByClinicId(@PathVariable Integer clinicId) {
        return administratorService.findAllAdministratorsByClinicId(clinicId);
    }

    @PostMapping
    public ResponseEntity<Administrator> add(@RequestBody @Valid Administrator administrator) {
        return administratorService.add(administrator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrator> update(@PathVariable Integer id, @RequestBody @Valid Administrator administrator) {

        return administratorService.update(id, administrator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Administrator> delete(@PathVariable Integer id) {
        return administratorService.delete(id);
    }

}
