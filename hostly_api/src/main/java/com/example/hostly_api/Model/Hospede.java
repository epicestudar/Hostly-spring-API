package com.example.hostly_api.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "hospedes")
@Getter
@Setter
public class Hospede implements Serializable {

    @Id
    private String id; // ID do hóspede

    @NotBlank(message = "O nome é obrigatório")
    private String nome; // Nome do hóspede

    @NotBlank(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento; // Data de nascimento do hóspede

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone; // Telefone do hóspede

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf; // CPF do hóspede

    @NotBlank(message = "O email é obrigatório")
    private String email; // Email do hóspede

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha; // Senha do hóspede

    private List<Reserva> reservas; // Lista de reservas do hóspede
}
