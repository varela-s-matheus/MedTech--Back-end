package com.br.medtech.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios_acesso")
public class UserAcess {
    //Classe para armazenamento de senhas e informações para Login
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    private char userType;
    private Long registerId;

    public UserAcess(Long registerId, String email, String password, char userType) {
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.registerId = registerId;
    }
}
