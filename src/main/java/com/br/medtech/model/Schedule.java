package com.br.medtech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "consultas")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Date date;

    @Future
    @NotNull
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