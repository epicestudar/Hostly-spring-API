<br>
<br>
<br>
<p align="center">
   <img src="/src/logo/logo.png" alt="logo" width=250px>
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

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Recursos" alt="Typing SVG"/></a>

**Equipe:**
- 1 Gerente de Projetos
- 3 Desenvolvedores Full-Stack (Spring Boot e Java)
- 1 Administrador de Banco de Dados
- 1 Especialista em Segurança

**Tecnologias:**
- **Spring Boot** (API)
- **Java Swing** (Interface)
- **MongoDB** (Banco de Dados)
- **Git/GitHub** (Controle de Versão)

**Ferramentas de Gestão:**
- Trello para gerenciamento de tarefas
- Slack para comunicação interna
- Mermaid para montagem dos diagramas

<br><br><br><br><br>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Diagrama de Classe" alt="Typing SVG" /></a>

```mermaid
classDiagram
    class Administrador {
        +string id_administrador
        +string email
        +string senha
        +post()
    }

    class Hospede {
        +string id_hospede
        +string nome
        +date data_nascimento
        +string telefone
        +string cpf
        +string email
        +string senha
        +list<Reserva> reservas
        +post()
        +get()
        +put()
        +delete()
    }

    class Quarto {
        +string id_quarto
        +string codigo_quarto
        +enum tipo_quarto
        +integer capacidade_quarto
        +double valor_quarto
        +enum status
        +list<Reserva> reservas
        +post()
        +get()
        +put()
        +delete()
    }

    class Reservas {
        +string id_reserva
        +foreignkey codigo_quarto
        +foreignkey cpf_hospede
        +foreignkey nome_hospede
        +integer quantidade_diarias
        +localdate data_check_in
        +localdate data_check_out
        +enum status
        +date data_reserva
        +post()
        +get()
        +put()
        +delete()
    }

    class Pagamento {
        +string id_pagamento
        +foreignkey id_reserva
        +foreignkey cpf_hospede
        +localdate data_pagamento
        +double valor_pago
        +enum metodo_pagamento
        +post()
        +get()
        +put()
        +delete()
        +calcularValorPagamento()
    }

    Administrador "1" -- "0..*" Hospede : "gerencia"
    Administrador "1" -- "0..*" Quarto : "gerencia"
    Hospede "1" -- "0..*" Reservas : "realiza"
    Quarto "1" -- "0..*" Reservas : "é reservado em"
    Reservas "1" -- "0..1" Pagamento : "possui"

```
<br><br><br><br><br>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Diagrama de Uso" alt="Typing SVG" /></a>
```mermaid
flowchart TD
    H[Hóspede] -->|Fazer Login| B(Fazer Login)
    B -->|Acessar Sistema| C(Sistema de Reservas)
    
    C -->|Buscar Quartos Disponíveis| D(Visualizar Quartos)
    D -->|Selecionar Quarto| E(Escolher Quarto e Ver Detalhes)
    E -->|Solicitar Reserva| F(Selecionar Datas e Confirmar Reserva)
    F -->|Efetuar Pagamento| G(Realizar Pagamento)
    G -->|Confirmar| H2(Reserva Confirmada)

    A2[Administrador] -->|Cadastrar Quartos| J(Cadastrar Novos Quartos)
    J -->|Definir Atributos do Quarto| K(Definir Número, Andar, Preço, Capacidade, etc.)
    
    A2 -->|Gerenciar Quartos| L(Gerenciar Quartos Existentes)
    L -->|Editar Quarto| M(Editar Detalhes do Quarto)
    L -->|Excluir Quarto| N(Excluir Quarto)
    
    A2 -->|Cadastrar Hóspede| X(Cadastrar Hóspede)
    A2 -->|Gerenciar Hóspede| Y(Gerenciar Hóspede Existente)
    
    A2 -->|Ver Reservas| Z(Visualizar Reservas)
    Z -->|Ver Pagamentos| W(Emissões de Pagamento)
    
    H -->|Visualizar Reservas| O(Ver Minhas Reservas Atuais)
    O -->|Cancelar Reserva| P(Cancelar Reserva)
    
    H -->|Editar Perfil| Q(Atualizar Informações de Hóspede)


```
<br><br><br><br><br>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Diagrama de Fluxo" alt="Typing SVG" /></a>
```mermaid
flowchart TD
    Start([Início]) -->|Acesso ao Sistema| A[Fazer Login]
    
    A -->|Hóspede| B{Login Bem-sucedido?}
    B -- Sim --> C[Acessar Sistema de Reservas]
    B -- Não --> D[Exibir Erro e Tentar Novamente]

    C -->|Ver Quartos Disponíveis| E[Visualizar Quartos]
    E --> F{Quartos Disponíveis?}
    F -- Sim --> G[Selecionar Quarto]
    F -- Não --> H[Exibir Mensagem: Sem Quartos Disponíveis]
    
    G --> I[Definir Datas de Check-in e Check-out]
    I --> J[Confirmar Reserva]
    J --> K{Efetuar Pagamento?}
    K -- Sim --> L[Escolher Método de Pagamento]
    L --> M[Efetuar Pagamento]
    M --> O[Reserva Confirmada]
    
    O --> End([Fim])

    C -->|Gerenciar Reservas| P[Visualizar Minhas Reservas]
    P --> Q[Cancelar Reserva]
    Q --> R[Reserva Cancelada]

    A -->|Administrador| S{Login Bem-sucedido?}
    S -- Sim --> T[Dashboard de Administração]
    T --> U[Cadastrar Novo Quarto]
    T --> V[Editar Quarto]
    T --> W[Excluir Quarto]
    
    T --> X[Gerenciar Hóspedes]
    X --> Y[Cadastrar Hóspede]
    X --> Z[Editar Hóspede]
    X --> AA[Excluir Hóspede]
    
    T --> AB[Visualizar Reservas]
    AB --> AC[Emitir Pagamento]
    
    S -- Não --> D

```
<br><br><br><br><br>
<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Manual do Usuário" alt="Typing SVG" />

### Acesso ao Sistema

1. **Realizar Cadastro (apenas hóspedes realizam cadastro):**
   - Na tela inicial, clique na opção **"Cadastrar"**.
   - Preencha todos os campos obrigatórios (nome, CPF, telefone, e-mail e senha) e clique em **"Salvar"**.
   - Após o cadastro, você receberá uma confirmação de que seu cadastro foi realizado com sucesso.

2. **Fazer Login:**
   - Após o cadastro, insira seu **e-mail** e **senha** nos campos apropriados.
   - Clique no botão **"Fazer Login"** para acessar o sistema.

### Para Hóspedes

3. **Visualizar Quartos Disponíveis:**
   - Após o login, você será direcionado para o sistema de reservas.
   - Clique na opção **"Ver Quartos Disponíveis"** para listar os quartos que podem ser reservados.

4. **Selecionar Quarto:**
   - Escolha um quarto da lista para visualizar os detalhes (número, capacidade, preço, etc.).
   - Clique em **"Solicitar Reserva"** para iniciar o processo de reserva.

5. **Definir Datas de Check-in e Check-out:**
   - Insira as datas desejadas para check-in e check-out.
   - Clique em **"Confirmar Reserva"**.

6. **Efetuar Pagamento:**
   - Escolha um método de pagamento (cartão de crédito, débito, etc.).
   - Clique em **"Efetuar Pagamento"** para finalizar a reserva.
   - Você verá uma mensagem de **"Reserva Confirmada"** após a conclusão do pagamento.

7. **Visualizar Reservas:**
   - Acesse a opção **"Minhas Reservas"** para ver todas as reservas feitas.
   - Você pode cancelar reservas existentes, se necessário.

8. **Editar Perfil:**
   - Acesse a opção **"Editar Perfil"** para atualizar suas informações pessoais, como telefone e endereço de e-mail.

### Para Administradores

9. **Acessar Dashboard:**
   - Após o login, você será direcionado ao **Dashboard de Administração**.
   - Aqui, você pode gerenciar quartos e hóspedes, além de visualizar reservas.

10. **Cadastrar Novo Quarto:**
    - Clique em **"Cadastrar Novo Quarto"** e preencha os campos necessários (número, andar, preço, capacidade, etc.).
    - Clique em **"Salvar"** para adicionar o quarto ao sistema.

11. **Gerenciar Quartos Existentes:**
    - Acesse a opção **"Gerenciar Quartos"** para visualizar todos os quartos cadastrados.
    - Você pode editar ou excluir quartos clicando nas opções correspondentes ao lado de cada quarto.

12. **Gerenciar Hóspedes:**
    - Clique em **"Gerenciar Hóspedes"** para visualizar e gerenciar os dados dos hóspedes.
    - Você pode cadastrar novos hóspedes, editar informações existentes ou excluir hóspedes.

13. **Visualizar Reservas:**
    - Acesse **"Visualizar Reservas"** para ver todas as reservas feitas por hóspedes.
    - Você pode emitir pagamentos e acessar extratos de emissão fiscal.
<br><br><br><br><br>
<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=440&size=22&pause=1000&color=38F77CFF&center=false&vCenter=false&repeat=false&width=435&lines=Testes Unitários" alt="Typing SVG" />

### - [Pasta de Testes Unitários](https://github.com/epicestudar/Hostly-spring-API/tree/main/hostly_api/src/main/java/com/example/hostly_api/Testes).

