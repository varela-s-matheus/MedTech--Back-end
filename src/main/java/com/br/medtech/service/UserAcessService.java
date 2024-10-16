package com.br.medtech.service;

import com.br.medtech.model.UserAcess;
import com.br.medtech.repository.UserAcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserAcessService implements UserDetailsService {

    @Autowired
    private UserAcessRepository userAcessRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Optional<UserAcess>> findUserAcessById(int id) {
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
        return ResponseEntity.ok(userAcessRepository.findByEmail(email).get());
    }

    public ResponseEntity<List<UserAcess>> findAllUserAcesss() {
        return ResponseEntity.ok(userAcessRepository.findAll());
    }

    public ResponseEntity<UserAcess> add(UserAcess userAcess) {
        try {
            userAcess.setPassword(passwordEncoder.encode(userAcess.getPassword()));
            return ResponseEntity.ok(userAcessRepository.saveAndFlush(userAcess));
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<UserAcess> update(int id, UserAcess userAcess) {
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

    public ResponseEntity<UserAcess> delete(int id){
        if (!userAcessRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }

        try {
            userAcessRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public boolean existsByEmail(String email) {
        return userAcessRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAcessRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

    }

}
