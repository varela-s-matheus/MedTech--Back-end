package com.br.medtech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pacientes")
public class Patient extends User {

    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    @Transient
    private final char userType = 'p';

}
