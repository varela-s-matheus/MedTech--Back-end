package com.br.medtech.repository;

import com.br.medtech.model.UserAcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAcessRepository extends JpaRepository<UserAcess, Integer> {

    Optional<UserAcess> findByEmail(String email);

    boolean existsByEmail(String email);
}
