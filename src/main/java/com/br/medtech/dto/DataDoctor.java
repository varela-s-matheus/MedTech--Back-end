package com.br.medtech.dto;

import java.util.Date;

public record DataDoctor(

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
        String crm,
        String specialty
        
) {
}
