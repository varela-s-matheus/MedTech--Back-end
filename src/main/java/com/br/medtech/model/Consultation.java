package com.br.medtech.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "consultas")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    private LocalTime initialHour;

    private LocalTime finalHour;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private String paymentMethod;

    private double paymentAmount;

    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    private boolean status;

    private long fkPatientId;

    private long fkDoctorId;


}