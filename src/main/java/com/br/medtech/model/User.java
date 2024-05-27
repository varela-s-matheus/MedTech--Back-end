package com.br.medtech.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String cpf;

    private Date birthDay;

    private String phone;

    private String cep;

    private String adress;

    private int adressNumber;

    private String neighborhood;

    private String city;
    @Transient
    private String password;
    @Transient
    private String email;


    // Methods ---------------------------

}
