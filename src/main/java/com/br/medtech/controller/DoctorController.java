package com.br.medtech.controller;

import com.br.medtech.model.Doctor;
import com.br.medtech.service.DoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> findDoctorById(@PathVariable Integer id) {
        return doctorService.findDoctorById(id);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> findAllDoctors() {
        return doctorService.findAllDoctors();
    }

    @PostMapping
    public ResponseEntity<Doctor> add(@RequestBody Doctor doctor) {
        return doctorService.add(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Integer id, @RequestBody Doctor doctor) {
        return doctorService.update(id, doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> delete(@PathVariable Integer id) {
        return doctorService.delete(id);
    }

}
