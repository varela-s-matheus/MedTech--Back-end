package com.br.medtech.repository;

import com.br.medtech.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Boolean existsPatientByCpf(String cpf);
}
