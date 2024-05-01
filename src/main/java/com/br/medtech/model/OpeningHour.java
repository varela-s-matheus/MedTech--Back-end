package com.br.medtech.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
@Data
@Entity
@Table(name = "horarios_atendimentos")
public class OpeningHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalTime firstPeriodStart;
    private LocalTime firstPeriodEnd;
    private LocalTime secondPeriodStart;
    private LocalTime secondPeriodEnd;
    private String dayWeek;
    private long fkDoctorId;


}
