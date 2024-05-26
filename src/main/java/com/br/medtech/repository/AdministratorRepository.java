package com.br.medtech.repository;

import com.br.medtech.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    @Query(value = "SELECT a.* " +
            "FROM administrador_consultorio ac " +
            "RIGHT JOIN administradores a ON ac.fk_administrator_id = a.id " +
            "WHERE ac.fk_consultorio_id = :clinicId", nativeQuery = true)
    public List<Administrator> findAllAdministratorsByClinicId(Integer clinicId);
}
