package com.br.medtech.service;

import com.br.medtech.model.Doctor;
import com.br.medtech.model.Patient;
import com.br.medtech.model.UserAcess;
import com.br.medtech.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PatienteService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserAcessService userAcessService;

    public ResponseEntity<Optional<Patient>> findPatientById(Integer id) {
        try {
            Optional<Patient> patient = patientRepository.findById(id);

            if (patient.isPresent()) {
                return ResponseEntity.ok(patient);
            } throw new RuntimeException();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado no banco de dados. " + e);
        }
    }

    public ResponseEntity<List<Patient>> findAllPatients() {
        return ResponseEntity.ok(patientRepository.findAll());
    }

    public ResponseEntity<Patient> add(Patient patient) {
        try {
            if(!patientRepository.existsPatientByCpf(patient.getCpf()) && !userAcessService.existsByEmail(patient.getEmail())) {
                Patient savedPatient = patientRepository.saveAndFlush(patient);
                userAcessService.add(new UserAcess(patient.getId(), patient.getEmail(), patient.getPassword(), patient.getUserType()));
                return ResponseEntity.ok(savedPatient);
            }
            throw new RuntimeException("Usuário já cadastrado!");
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Patient> update(Integer id, Patient Patient) {
        if (!patientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado no banco de dados.");
        }
        Patient.setId(id);

        try {
            final Patient updatePatient = patientRepository.save(Patient);
            return ResponseEntity.ok(updatePatient);
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Patient> delete(Integer id){
        if (!patientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado no banco de dados.");
        }

        try {
            patientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
