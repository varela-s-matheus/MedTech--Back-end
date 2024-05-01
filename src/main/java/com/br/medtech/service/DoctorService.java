package com.br.medtech.service;

import com.br.medtech.model.Doctor;
import com.br.medtech.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;

    public ResponseEntity<Optional<Doctor>> findDoctorById(Long id) {
        try {
            Optional<Doctor> doctor = doctorRepository.findById(id);

            if (doctor.isPresent()) {
                return ResponseEntity.ok(doctor);
            } throw new RuntimeException();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado no banco de dados. " + e);
        }
    }

    public ResponseEntity<List<Doctor>> findAllDoctors() {
        return ResponseEntity.ok(doctorRepository.findAll());
    }

    public ResponseEntity<Doctor> add(Doctor doctor) {
        try {
            return ResponseEntity.ok(doctorRepository.saveAndFlush(doctor));
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Doctor> update(Long id, Doctor doctor) {
        if (!doctorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado no banco de dados.");
        }
        doctor.setId(id);

        try {
            final Doctor updateDoctor = doctorRepository.save(doctor);
            return ResponseEntity.ok(updateDoctor);
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Doctor> delete(Long id){
        if (!doctorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado no banco de dados.");
        }

        try {
            doctorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
