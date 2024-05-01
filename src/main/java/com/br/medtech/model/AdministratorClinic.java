package com.br.medtech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Table(name = "administrador_consultorio")
public class AdministratorClinic {
    private Long fk_consultorio_id;
    private Long fk_administrator_id;

}
