package com.example.hostly_api.Model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.example.hostly_api.Enum.StatusReserva;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class Reserva implements Serializable{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_reserva;

    @NotNull(message = "O código do quarto é obrigatório")
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY) // isso significa que as entidades associadas só serão
                                                           // carregadas quando realmente forem acessadas, o que é uma
                                                           // boa prática para melhorar o desempenho.
    @JoinColumn(name = "codigo_quarto", referencedColumnName = "codigo_quarto", nullable = false)
    private Quarto quarto;

    @NotNull(message = "O cpf do hospede é obrigatório")
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY) // isso significa que as entidades associadas só serão
                                                           // carregadas quando realmente forem acessadas, o que é uma
                                                           // boa prática para melhorar o desempenho.
    @JoinColumn(name = "cpf_hospede", referencedColumnName = "cpf", nullable = false)
    private Hospede hospede;

    @NotNull(message = "O nome do hospede é obrigatório")
    @Column(name = "nome_hospede", length = 100, nullable = false)
    private String nome_hospede;

    @NotNull(message = "A quantidade de diarias é obrigatório")
    @Column(name = "quantidade_diarias", nullable = false)
    @Min(value = 1, message = "A quantidade mínima de diárias é 1")
    @Max(value = 30, message = "A quantidade máxima de diárias é 30")
    private Integer quantidade_diarias;

    @NotNull(message = "A data de check-in é obrigatória")
    @Column(name = "data_check_in", nullable = false)
    private LocalDate data_check_in;

    @NotNull(message = "A data de check-out é obrigatoria")
    @Column(name = "data_check_out", nullable = false)
    private LocalDate data_check_out;

    @Enumerated(jakarta.persistence.EnumType.STRING) // Define que o valor será salvo como uma string no banco
    @Column(name = "status_reserva", length = 10, nullable = false)
    private StatusReserva status = StatusReserva.PENDENTE;

    @Column(name = "data_reserva", nullable = false)
    private Data data_reserva;
}
