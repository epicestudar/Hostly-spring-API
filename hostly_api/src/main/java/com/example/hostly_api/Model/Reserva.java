package com.example.hostly_api.Model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.hostly_api.Enum.StatusReserva;

@Document(collection = "reservas")
@Getter
@Setter
public class Reserva implements Serializable {
    @Id
    private String id;

    @NotNull(message = "O código do quarto é obrigatório")
    private Quarto quarto;

    @NotNull(message = "O cpf do hospede é obrigatório")
    private Hospede hospede;

    @NotNull(message = "A quantidade de diarias é obrigatória")
    @Min(value = 1, message = "A quantidade mínima de diárias é 1")
    @Max(value = 30, message = "A quantidade máxima de diárias é 30")
    private Integer quantidadeDiarias;

    @NotNull(message = "A data de check-in é obrigatória")
    private LocalDate dataCheckIn;

    @NotNull(message = "A data de check-out é obrigatória")
    private LocalDate dataCheckOut;

    private StatusReserva status = StatusReserva.PENDENTE;

    private LocalDate dataReserva; 
}
