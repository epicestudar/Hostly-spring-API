package com.example.hostly_api.Model;

import com.example.hostly_api.Enum.StatusQuarto;
import com.example.hostly_api.Enum.TipoQuarto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "quartos")
@Getter
@Setter
public class Quarto {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_quarto;

    @NotBlank(message = "O código do quarto é obrigatório")
    @Column(name = "codigo_quarto", length = 10, nullable = false, unique = true)
    private String codigo_quarto;

    @Enumerated(jakarta.persistence.EnumType.STRING) // Salvar como string no banco
    @Column(name = "tipo_quarto", nullable = false)
    private TipoQuarto tipo_quarto = TipoQuarto.STANDARD;

    @NotNull(message = "A capacidade do quarto é obrigatório")
    @Column(name = "capacidade_quarto", length = 3, nullable = false)
    private Integer capacidade_quarto;

    @NotNull(message = "O valor do quarto é obrigatório")
    @Column(name = "valor_quarto", precision = 10, scale = 2, nullable = false)
    private Double valor_quarto;

    @Enumerated(jakarta.persistence.EnumType.STRING) // Define que o valor será salvo como uma string no banco
    @Column(name = "status_quarto", length = 10, nullable = false)
    private StatusQuarto status = StatusQuarto.DISPONIVEL; // Define o valor default como DISPONIVEL

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

}
