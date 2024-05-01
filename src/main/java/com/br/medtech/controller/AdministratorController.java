package com.br.medtech.controller;

import com.br.medtech.model.Administrator;
import com.br.medtech.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Administrator>> findAdministratorById(@PathVariable Long id) {
        return administratorService.findAdministratorById(id);
    }

    @GetMapping
    public ResponseEntity<List<Administrator>> findAllAdministrators() {
        return administratorService.findAllAdministrators();
    }

    @PostMapping
    public ResponseEntity<Administrator> add(@RequestBody Administrator administrator) {
        return administratorService.add(administrator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrator> update(@PathVariable Long id, @RequestBody Administrator administrator) {
        return administratorService.update(id, administrator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Administrator> delete(@PathVariable Long id) {
        return administratorService.delete(id);
    }

}
