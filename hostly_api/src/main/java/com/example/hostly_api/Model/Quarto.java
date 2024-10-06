package com.example.hostly_api.Model;

import com.example.hostly_api.Enum.StatusQuarto;
import com.example.hostly_api.Enum.TipoQuarto;
import java.io.Serializable;
import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quartos")
@Getter
@Setter
public class Quarto implements Serializable {
    @Id
    private String id; // O MongoDB vai gerar o ID automaticamente se for uma String

    @NotBlank(message = "O código do quarto é obrigatório")
    private String codigoQuarto;

    // O MongoDB irá armazenar automaticamente como string
    private TipoQuarto tipoQuarto = TipoQuarto.STANDARD;

    @NotNull(message = "A capacidade do quarto é obrigatória")
    private Integer capacidadeQuarto;

    @NotNull(message = "O valor do quarto é obrigatório")
    private Double valorQuarto;

    // O MongoDB irá armazenar automaticamente como string
    private StatusQuarto status = StatusQuarto.DISPONIVEL;

    private List<Reserva> reservas;
}
