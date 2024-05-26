package com.br.medtech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pacientes")
public class Patient extends User {

    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

}
