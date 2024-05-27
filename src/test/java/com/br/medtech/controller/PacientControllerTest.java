package com.br.medtech.controller;

import com.br.medtech.dto.DataPatient;
import com.br.medtech.model.HealthInsurance;
import com.br.medtech.service.PatienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PacientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataPatient> dataPatientJacksonTester;

    @MockBean
    private PatienteService patienteService;

    @Test
    @DisplayName("Deve retornar http 200 Requisição valida e retornar paciente")
    @WithMockUser
    void addPatient() throws Exception {
        Date birthDay = new Date(2002, Calendar.JULY,12);

        var response = mvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataPatientJacksonTester.write(
                                new DataPatient("Matheus", "12345678910", birthDay,
                                        "123456789101", "89010000", "Rua Xpto", 265,
                                        "Nova Esperança", "Blumenau", "12345678910",
                                        "Matheus@hotmail.com", HealthInsurance.UNIMED))
                                .getJson()))
                                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    @DisplayName("Deve retornar http 400 quando requisição é inválida")
    void findPatientByIdCenario01() throws Exception {
        var response = mvc.perform(post("/patient"))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

}