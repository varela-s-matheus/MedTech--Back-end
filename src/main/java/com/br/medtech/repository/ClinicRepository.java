package com.br.medtech.repository;

import com.br.medtech.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    Clinic findByName(String name);

}
