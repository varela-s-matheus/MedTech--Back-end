package com.br.medtech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;

import java.util.List;

import org.hibernate.annotations.CascadeType;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "administradores")
public class Administrator extends User {

    @JsonBackReference
    @Cascade(CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "administrador_consultorio",
            joinColumns = @JoinColumn(name = "fk_administrator_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_consultorio_id")
    )
    private List<Clinic> clinics;

    public void addClinics(Clinic clinics) {
        this.clinics.add(clinics);
    }
}