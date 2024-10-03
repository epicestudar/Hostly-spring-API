package com.example.hostly_api.Model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "hospedes")
@Getter
@Setter
public class Hospede {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_hospede;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotBlank(message = "A data de nascimento é obrigatória")
    @Column(name = "data_nascimento", nullable = false)
    private Data data_nascimento;

    @NotBlank(message = "O telefone é obrigatório")
    @Column(name = "telefone", length = 20, nullable = false)
    private String telefone;

    @NotBlank(message = "O CPF é obrigatório")
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @NotBlank(message = "O email é obrigatório")
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Column(name = "senha", length = 60, nullable = false)
    private String senha;

    @OneToMany(mappedBy = "hospede", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;
}
