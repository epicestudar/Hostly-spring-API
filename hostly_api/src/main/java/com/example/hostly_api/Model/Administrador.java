package com.example.hostly_api.Model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "administrador")
@Getter
@Setter
public class Administrador implements Serializable {
    @Id
    private String id;

    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;
}
