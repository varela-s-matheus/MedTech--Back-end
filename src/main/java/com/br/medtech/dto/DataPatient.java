package com.br.medtech.dto;

import com.br.medtech.model.HealthInsurance;
import java.util.Date;

public record DataPatient(

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
        HealthInsurance healthInsurance     ) {
}
