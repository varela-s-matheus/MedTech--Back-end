package com.br.medtech.dto;

import com.br.medtech.model.Clinic;

import java.util.Date;
import java.util.List;

public record DataAdministrator(

        String name,
        String cpf,
        Date birthDay,
        String phone,
        String cep,
        String adress,
        int adressNumber,
        String neighborhood,
        String city,
        String password,
        String email,
        List<Clinic> clinics) {
}
