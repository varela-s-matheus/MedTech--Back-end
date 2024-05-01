package com.br.medtech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "medicos")
public class Doctor extends User {
    private String crm;
    private String specialty;

}
