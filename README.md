<br>
<br>
<br>
<p align="center">
   <img src="/logo/logo.png" alt="logo" width=250px>
</p>

<p align="center">
   <img src="https://img.shields.io/badge/API-FEITO-blue?style=for-the-badge" alt="backend" />
  <img src="https://img.shields.io/badge/Documentação-FEITO-blue?style=for-the-badge" alt="documentação" />
  <img src="https://img.shields.io/badge/Manual-FEITO-blue?style=for-the-badge" alt="mobile" />
  <img src="https://img.shields.io/badge/Interface-FEITO-blue?style=for-the-badge" alt="site" />
</p>
<hr>
<br>
<br><br><br>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Introdução" alt="Typing SVG" /></a>

## Documentação da API - Sistema de Controle de Reservas de Hotel

### Visão Geral do Projeto
**Objetivo:**
A API do Sistema de Controle de Reservas de Hotel é responsável por permitir a autenticação de hóspedes, gerenciar reservas e listar quartos disponíveis. O sistema é integrado a uma interface em Java Swing usada por administradores para operações CRUD de hóspedes e quartos. Esta API utiliza Spring Boot e conecta-se ao MongoDB para persistência de dados.

<br><br><br><br><br>
<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Tecnologias Utilizadas" alt="Typing SVG" /></a>

- **Java 17** (ou superior)  
- **Spring Boot**  
  - Spring Web  
  - Spring Data MongoDB  
  - Spring DevTools  
  - Lombok  
  - Thymeleaf  
- **MongoDB**  
- **Maven** para gerenciamento de dependências  
- **Thunder Client** para testes de API  
<br><br><br><br><br>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Instalação e Configuração" alt="Typing SVG"/></a>

### Clone o repositório:

```bash
git clone https://github.com/epicestudar/Hostly-spring-API.git
```

### Acesse o diretório:

```bash
cd hostly_api
```

### Configure o application.properties:

```bash
spring.data.mongodb.uri=mongodb://localhost:27017/hostly
```

### Instale as dependências no seu terminal:

```bash
mvn clean install
```

### Execute o projeto:

```bash
mvn spring-boot:run
```

### - Certifique-se de ter o [git](https://git-scm.com/downloads) e [maven](https://maven.apache.org/download.cgi) instalados.

<br><br><br><br><br>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Estrutura dos Endpoints" alt="Typing SVG" /></a>

### 4.1 Cadastro do Administrador

#### **POST /api/administrador**  
Cadastra um administrador no sistema.

- **URL para o Thunder Client:**  
  `http://localhost:8080/api/administrador`

#### **Request Body:**
```json
{
  "email": "admin@hotel.com",
  "senha": "12345678"
}
```

#### **Response (201):**

```json
{
  "mensagem": "Administrador cadastrado com sucesso!"
}
```
<br><br><br><br><br>
### 4.2 Cadastro do Hóspede

#### **POST /api/hospedes**  
Cadastra um hóspede no sistema.

- **URL para o Thunder Client:**  
  `http://localhost:8080/api/hospedes`

#### **Request Body:**
```json
{
    "nome": "Vinicius",
    "dataNascimento": "2006-02-06",
    "telefone": "5519982501287",
    "cpf": "12345678901",
    "email": "vini@email.com",
    "senha": "12345678"   
}
```

#### **Response (201):**

```json
{
  "mensagem": "Hóspede cadastrado com sucesso!"
}
```
<br><br><br><br><br>

### 4.3 Cadastro do Quarto

#### **POST /api/quartos**  
Cadastra um quarto no sistema.

- **URL para o Thunder Client:**  
  `http://localhost:8080/api/quartos`

#### **Request Body:**
```json
{
    "codigoQuarto": "2A",
    "tipoQuarto": "SUITE",
    "capacidadeQuarto": 3,
    "valorQuarto": 400.0
}
```

#### **Response (201):**

```json
{
  "mensagem": "Quarto cadastrado com sucesso!"
}
```


<br><br><br><br><br>

### 4.4 Realização de Reserva

#### **POST /api/reservas**  
Realizar uma reserva de quarto.

- **URL para o Thunder Client:**  
  `http://localhost:8080/api/reservas`

#### **Request Body:**
```json
{
   {
    "id": "id_gerado_automaticamente",
    "quarto": {
      "id": "id_respectivo_do_quarto",
      "codigoQuarto": "2A",
      "tipoQuarto": "SUITE",
      "capacidadeQuarto": 3,
      "valorQuarto": 400.0,
      "status": "RESERVADO",
      "reservas": null
    },
    "hospede": {
      "id": "id_respectivo_do_hospede",
      "nome": "Davizao",
      "dataNascimento": "2005-10-10",
      "telefone": "12929832384",
      "cpf": "12345678908",
      "email": "davi@email.com",
      "senha": "12345678",
      "reservas": null
    },
    "quantidadeDiarias": 3,
    "dataCheckIn": "2024-10-16",
    "dataCheckOut": null,
    "status": "CONFIRMADO",
    "dataReserva": "2024-10-16"
  }
}
```

#### **Response (201):**

```json
{
  "mensagem": "Reserva realizada com sucesso!"
}
```


<br><br><br><br><br>
