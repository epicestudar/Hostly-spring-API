package com.example.hostly_api.Model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.hostly_api.Enum.MetodoPagamento;

@Document(collection = "pagamentos")
@Getter
@Setter
public class Pagamento implements Serializable {

    @Id
    private String id; // ID do pagamento

    @NotNull(message = "O ID da reserva é obrigatório")
    private Reserva reserva; // Referência à Reserva

    @NotNull(message = "O CPF do hóspede é obrigatório")
    private Hospede hospede; // Referência ao Hóspede

    @NotNull(message = "A data do pagamento é obrigatória")
    private LocalDate dataPagamento; // Data do pagamento

    @NotNull(message = "O valor pago é obrigatório")
    private Double valorPago; // Valor pago

    private MetodoPagamento metodoPagamento = MetodoPagamento.DINHEIRO;

    // Método para calcular o valor total do pagamento
    public Double calcularValorPagamento() {
        // Obter o valor do quarto com base no tipo de quarto
        Double valorPorDiaria = reserva.getQuarto().getTipoQuarto().getValorDiaria();
        Integer quantidadeDiarias = reserva.getQuantidadeDiarias();

        // Calcular e retornar o valor total
        return valorPorDiaria * quantidadeDiarias;
    }
}
