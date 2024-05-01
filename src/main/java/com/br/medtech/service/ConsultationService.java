package com.br.medtech.service;

import com.br.medtech.model.Consultation;
import com.br.medtech.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public ResponseEntity<Optional<Consultation>> findConsultationById(Long id) {
        try {
            Optional<Consultation> consultation = consultationRepository.findById(id);

            if (consultation.isPresent()) {
                return ResponseEntity.ok(consultation);
            } throw new RuntimeException();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado no banco de dados. " + e);
        }
    }

    public ResponseEntity<List<Consultation>> findAllConsultations() {
        return ResponseEntity.ok(consultationRepository.findAll());
    }

    public ResponseEntity<Consultation> add(Consultation consultation) {
        try {
            return ResponseEntity.ok(consultationRepository.saveAndFlush(consultation));
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Consultation> update(Long id, Consultation consultation) {
        if (!consultationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado no banco de dados.");
        }
        consultation.setId(id);

        try {
            final Consultation updateConsultation = consultationRepository.save(consultation);
            return ResponseEntity.ok(updateConsultation);
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Consultation> delete(Long id){
        if (!consultationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado no banco de dados.");
        }

        try {
            consultationRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
