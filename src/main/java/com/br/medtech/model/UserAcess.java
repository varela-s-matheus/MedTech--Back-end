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
    private String userAcess;
    private String userType;
    private Long userId;


}
