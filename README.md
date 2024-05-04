# Projeto MedTech - Gestão de consultórios médicos

## Sobre o Projeto
O backend do projeto MedTech é uma plataforma que gerencia clínicas médicas, permitindo que pacientes encontrem consultórios e agendem consultas online. 
Médicos podem cadastrar-se, gerenciar seus consultórios e agendas, além de registrar atendimentos e integrar convênios médicos. 
Essa solução oferece uma gestão completa e eficiente, melhorando o acesso e a experiência tanto para pacientes quanto para profissionais de saúde.

Aplicação desenvolvida em Java usando o framework SpringBoot. Back-end responsável por lidar com a lógicas de negócio, manipulação de dados e comunicação com o banco de dados, utilizando Spring Data JPA.

## Tecnologias Utilizadas
Este projeto utiliza a linguagem de programação JAVA com framework SpringBoot e SpringData JPA, para comunicação com o Banco de dados PostgreSQL.
Propondo uma comunicação com o Front-end através de API Rest. 


## Padrão MCV
projeto utiliza o modelo MVC(Model-View-Controller), com o foco na organização e separação de responsabilidades.

## Estruturação dos end-point:

Endereço padrão que irá rodar a aplicação: http://localhost:8080.

<strong>Administradores:</strong>
```
GET: "/administrator/:id" -> Buscar administrador por Id. 
GET: "/administrator/clinic/:idClinic" -> Buscar administradores pelo Id da clínica.
POST: "/administrator" -> Cadastrar administrador.
PUT: "/administrator/:id" -> Atualizar cadastro administrador.
DELETE: "/administrator/:id" -> Apagar administrador por Id. 
```

Em andamento...


## Configuração do Banco de Dados PostgreSQL

* Instale o PostgreSQL em sua máquina, caso ainda não estiver instalado.
* Crie um banco de dados para a aplicação utilizando o comando:
```
CREATE DATABASE "medtech"
WITH
OWNER = postgres
ENCODING = 'UTF8'
LOCALE_PROVIDER = 'libc'
CONNECTION LIMIT = -1
IS_TEMPLATE = False;
```
Criação das entidades do banco de dados e seus relacionamentos:
```
CREATE TABLE usuarios_acesso (
    id bigserial PRIMARY KEY,
    password VARCHAR(255),
    usuario VARCHAR(255),
    user_type CHAR,
    userId bigserial
);

CREATE TABLE consultorios (
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    cnpj VARCHAR(18),
    phone VARCHAR(15),
    cep VARCHAR(9),
    adress VARCHAR(255),
    complement VARCHAR(255),
    neighborhood VARCHAR(255),
    adress_number INT,
    city VARCHAR(255),
    specialization VARCHAR(255),
    accept_health_insurance varchar(50)
);

create table horarios_atendimentos (
    id bigserial primary key,
    first_period_start VARCHAR(50),
    first_period_end VARCHAR(50),
    day_week VARCHAR(50),
    second_period_start VARCHAR(50),
    second_period_end VARCHAR(50),
    fk_doctor_id bigserial
);

CREATE TABLE consultas(
    id bigserial PRIMARY KEY,
    date DATE,
    initial_hour time,
    final_hour time,
    service_type VARCHAR(255),
    payment_method VARCHAR(255),
    payment_amount DECIMAL,
    health_insurance VARCHAR(50),
    status boolean,
    fk_patient_id bigserial,
    fk_doctor_id bigserial
);

CREATE TABLE administradores (
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(14),
    birth_day DATE,
    phone VARCHAR(15),
    cep VARCHAR(9),
    adress VARCHAR(255),
    city VARCHAR(255),
    neighborhood VARCHAR(255),
    adress_number INT
);

CREATE TABLE medicos (
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(14),
    birth_day DATE,
    phone VARCHAR(15),
    cep VARCHAR(9),
    adress VARCHAR(255),
    city VARCHAR(255),
    neighborhood VARCHAR(255),
    adress_number INT,
    crm VARCHAR(50),
    specialty VARCHAR(50)
);

CREATE TABLE pacientes (
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(14),
    birth_day DATE,
    phone VARCHAR(15),
    cep VARCHAR(9),
    adress VARCHAR(255),
    city VARCHAR(255),
    neighborhood VARCHAR(255),
    adress_number INT,
    health_insurance VARCHAR(255)
);

CREATE TABLE administrador_consultorio (
    fk_consultorio_id bigserial,
    fk_administrator_id bigserial
);
 

ALTER TABLE horarios_atendimentos ADD CONSTRAINT FK_horarios_atendimentos_2
    FOREIGN KEY (fk_doctor_id)
    REFERENCES medicos (id)
    ON DELETE RESTRICT;
 
ALTER TABLE consultas ADD CONSTRAINT FK_consulta_1
    FOREIGN KEY (fk_patient_id)
    REFERENCES pacientes (id)
    ON DELETE CASCADE;
   
ALTER TABLE consultas ADD CONSTRAINT FK_consulta_2
    FOREIGN KEY (fk_doctor_id)
    REFERENCES medicos (id)
    ON DELETE CASCADE;
 
ALTER TABLE administrador_consultorio ADD CONSTRAINT FK_administrador_consultorio_1
    FOREIGN KEY (fk_consultorio_id)
    REFERENCES consultorios (id)
    ON DELETE RESTRICT;
 
ALTER TABLE administrador_consultorio ADD CONSTRAINT FK_administrador_consultorio_2
    FOREIGN KEY (fk_administrator_id)
    REFERENCES administradores (id)
    ON DELETE RESTRICT;
```

É muito importante verificar as variáveis de ambiente para realizar a conexão com o Banco em <strong>application.properties</strong>.


## Contatos

<a href="https://linkedin.com/in/varela-s-matheus" target="_blank">
  <img align="center" src="https://img.shields.io/badge/-MatheusVarela-05122A?style=flat&logo=linkedin" alt="linkedin"/>
</a>
<a href="https://www.instagram.com/varela_matheuus/" target="_blank">
 <img align="center" src="https://img.shields.io/badge/-MatheusVarela-05122A?style=flat&logo=instagram" alt="instagram"/>
</a>
