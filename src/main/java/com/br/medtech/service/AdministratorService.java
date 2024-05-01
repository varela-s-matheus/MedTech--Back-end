package com.br.medtech.service;

import com.br.medtech.model.Administrator;
import com.br.medtech.repository.AdministratorRepository;
import com.br.medtech.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    public ResponseEntity<Optional<Administrator>> findAdministratorById(Long id) {
        try {
            Optional<Administrator> administrator = administratorRepository.findById(id);

            if (administrator.isPresent()) {
                return ResponseEntity.ok(administrator);
            } throw new RuntimeException();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador não encontrado no banco de dados. " + e);
        }
    }

    public ResponseEntity<List<Administrator>> findAllAdministrators() {
        return ResponseEntity.ok(administratorRepository.findAll());
    }

    public ResponseEntity<Administrator> add(Administrator administrator) {
        try {
            return ResponseEntity.ok(administratorRepository.saveAndFlush(administrator));
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Administrator> update(Long id, Administrator administrator) {
        if (!administratorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador não encontrado no banco de dados.");
        }
        administrator.setId(id);

        try {
            final Administrator updateAdministrator = administratorRepository.save(administrator);
            return ResponseEntity.ok(updateAdministrator);
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Administrator> delete(Long id){
        if (!administratorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador não encontrado no banco de dados.");
        }

        try {
            administratorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
