package com.example.hostly_api.Model;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.hostly_api.Enum.MetodoPagamento;

@Entity
@Document(collection = "pagamentos")
@Getter
@Setter
public class Pagamento implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_pagamento; // ID do pagamento

    @NotNull(message = "O ID da reserva é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY) // Carregar a reserva apenas quando necessário
    @JoinColumn(name = "id_reserva", referencedColumnName = "id_reserva", nullable = false)
    private Reserva reserva; // Chave estrangeira para Reserva

    @NotNull(message = "O CPF do hóspede é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY) // Carregar o hóspede apenas quando necessário
    @JoinColumn(name = "cpf_hospede", referencedColumnName = "cpf", nullable = false)
    private Hospede hospede; // Chave estrangeira para Hospede

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate data_pagamento; // Data do pagamento

    @Column(name = "valor_pago", precision = 10, scale = 2, nullable = false)
    private Double valor_pago; // Valor pago

    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name = "metodo_pagamento", nullable = false) 
    private MetodoPagamento metodo_pagamento = MetodoPagamento.DINHEIRO;

    // Método para calcular o valor total do pagamento
    public Double calcularValorPagamento() {
        // Obter o valor do quarto com base no tipo de quarto
        Double valorPorDiaria = reserva.getQuarto().getTipo_quarto().getValorDiaria();
        Integer quantidadeDiarias = reserva.getQuantidade_diarias();

        // Calcular e retornar o valor total
        return valorPorDiaria * quantidadeDiarias;
    }
}
