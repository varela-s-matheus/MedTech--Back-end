package com.br.medtech.repository;

import com.br.medtech.model.UserAcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAcessRepository extends JpaRepository<UserAcess, Integer> {

    public UserAcess findByEmail(String email);


}
