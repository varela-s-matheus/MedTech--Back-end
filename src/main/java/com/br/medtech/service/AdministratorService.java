package com.br.medtech.service;

import com.br.medtech.model.Administrator;
import com.br.medtech.model.Doctor;
import com.br.medtech.model.UserAcess;
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
    
    @Autowired
    private UserAcessService userAcessService;

    public ResponseEntity<Optional<Administrator>> findAdministratorById(Integer id) {
        try {
            Optional<Administrator> administrator = administratorRepository.findById(id);

            if (administrator.isPresent()) {
                return ResponseEntity.ok(administrator);
            } throw new RuntimeException();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador não encontrado no banco de dados. " + e);
        }
    }

    public ResponseEntity<List<Administrator>> findAllAdministratorsByClinicId(Integer clinicId) {
        return ResponseEntity.ok(administratorRepository.findAllAdministratorsByClinicId(clinicId));
    }

    public ResponseEntity<Administrator> add(Administrator administrator) {
        try {
            userAcessService.add(new UserAcess(administrator.getId(), administrator.getEmail(),
                    administrator.getPassword(), administrator.getUserType()));

            if(!administratorRepository.existsByCpf(administrator.getCpf()) && !userAcessService.existsByEmail(administrator.getEmail())) {
                Administrator savedAdministrator = administratorRepository.saveAndFlush(administrator);
                userAcessService.add(new UserAcess(administrator.getId(), administrator.getEmail(), administrator.getPassword(), administrator.getUserType()));
                return ResponseEntity.ok(savedAdministrator);
            }
            throw new RuntimeException("Usuário já cadastrado!");
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Administrator> update(Integer id, Administrator administrator) {
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

    public ResponseEntity<Administrator> delete(Integer id){
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
