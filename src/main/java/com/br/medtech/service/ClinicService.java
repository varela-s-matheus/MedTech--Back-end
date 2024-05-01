package com.br.medtech.service;

import com.br.medtech.model.Clinic;
import com.br.medtech.model.Clinic;
import com.br.medtech.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public class ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;

    public Clinic findByName(String name) {
        return clinicRepository.findByName(name);
    }

    public ResponseEntity<Optional<Clinic>> findClinicById(Long id) {
        try {
            Optional<Clinic> clinic = clinicRepository.findById(id);

            if (clinic.isPresent()) {
                return ResponseEntity.ok(clinic);
            } throw new RuntimeException();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consutório não encontrado no banco de dados. " + e);
        }
    }

    public ResponseEntity<List<Clinic>> findAllClinics() {
        return ResponseEntity.ok(clinicRepository.findAll());
    }

    public ResponseEntity<Clinic> add(Clinic clinic) {
        try {
            return ResponseEntity.ok(clinicRepository.saveAndFlush(clinic));
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Clinic> update(Long id, Clinic clinic) {
        if (!clinicRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado no banco de dados.");
        }
        clinic.setId(id);

        try {
            final Clinic updateClinic = clinicRepository.save(clinic);
            return ResponseEntity.ok(updateClinic);
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Clinic> delete(Long id){
        if (!clinicRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado no banco de dados.");
        }

        try {
            clinicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
