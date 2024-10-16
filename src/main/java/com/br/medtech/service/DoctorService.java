package com.br.medtech.service;

import com.br.medtech.model.Doctor;
import com.br.medtech.model.Patient;
import com.br.medtech.model.UserAcess;
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

    @Autowired
    private UserAcessService userAcessService ;

    public ResponseEntity<Optional<Doctor>> findDoctorById(Integer id) {
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
            if(!doctorRepository.existsByCpf(doctor.getCpf()) && !userAcessService.existsByEmail(doctor.getEmail())) {
                Doctor savedDoctor = doctorRepository.saveAndFlush(doctor);
                userAcessService.add(new UserAcess(doctor.getId(), doctor.getEmail(), doctor.getPassword(), doctor.getUserType()));
                return ResponseEntity.ok(savedDoctor);
            }
            throw new RuntimeException("Usuário já cadastrado!");

        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Doctor> update(Integer id, Doctor doctor) {
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

    public ResponseEntity<Doctor> delete(Integer id){
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
