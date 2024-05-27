package com.br.medtech.service;

import com.br.medtech.model.UserAcess;
import com.br.medtech.repository.UserAcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserAcessService {
    @Autowired
    private UserAcessRepository userAcessRepository;

    public ResponseEntity<Optional<UserAcess>> findUserAcessById(Integer id) {
        try {
            Optional<UserAcess> userAcess = userAcessRepository.findById(id);

            if (userAcess.isPresent()) {
                return ResponseEntity.ok(userAcess);
            } throw new RuntimeException();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado no banco de dados. " + e);
        }
    }

    public ResponseEntity<UserAcess> findByEmail(String email) {
        return ResponseEntity.ok(userAcessRepository.findByEmail(email));
    }

    public ResponseEntity<List<UserAcess>> findAllUserAcesss() {
        return ResponseEntity.ok(userAcessRepository.findAll());
    }
    public ResponseEntity<UserAcess> add(long id, String email, String password, char typeUser) {
        try {
            UserAcess userAcess = new UserAcess(id, email, password, typeUser);
            return ResponseEntity.ok(userAcessRepository.saveAndFlush(userAcess));
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<UserAcess> update(Integer id, UserAcess userAcess) {
        if (!userAcessRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado no banco de dados.");
        }
        userAcess.setId(id);

        try {
            final UserAcess updateUserAcess = userAcessRepository.save(userAcess);
            return ResponseEntity.ok(updateUserAcess);
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<UserAcess> delete(Integer id){
        if (!userAcessRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado no banco de dados.");
        }

        try {
            userAcessRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
