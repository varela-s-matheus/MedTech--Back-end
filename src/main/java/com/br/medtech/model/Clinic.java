package com.br.medtech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "consultorios")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String cnpj;

    private String phone;

    private String cep;

    private String adress;

    private int adressNumber;

    private String neighborhood;

    private String city;

    private String complement;

    private String specialization;

    private String acceptHealthInsurance;

    @JsonBackReference
    @ManyToMany(mappedBy = "clinics")
    private List<Administrator> administrators;
}
