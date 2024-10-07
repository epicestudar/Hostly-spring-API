package com.example.hostly_api.RestController;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostly_api.Model.Hospede;
import com.example.hostly_api.Service.HospedeService;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeRestController {
    @Autowired
    private HospedeService hospedeService;

    // Buscar hóspede por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Hospede> buscarPorCpf(@PathVariable String cpf) {
        Optional<Hospede> hospede = hospedeService.buscarPorCpf(cpf);

        return hospede.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar hóspede por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Hospede> buscarPorEmail(@PathVariable String email) {
        Optional<Hospede> hospede = hospedeService.buscarPorEmail(email);

        return hospede.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Hospede> buscarPorId(@PathVariable String id) {
        Optional<Hospede> hospede = hospedeService.buscarPorId(id);

        return hospede.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar um novo hóspede
    @PostMapping
    public ResponseEntity<Hospede> salvar(@RequestBody Hospede hospede) {
        Hospede novoHospede = hospedeService.salvar(hospede);
        return ResponseEntity.ok(novoHospede);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");

        Optional<Hospede> hospedeOpt = hospedeService.buscarPorEmail(email);

        if (hospedeOpt.isPresent()) {
            Hospede hospede = hospedeOpt.get();
            if (hospede.getSenha().equals(senha)) {
                return ResponseEntity.ok("Login bem-sucedido!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hóspede não encontrado");
        }
    }

    // Atualizar um hóspede
    @PutMapping("/{id}")
    public ResponseEntity<Hospede> atualizar(@PathVariable String id, @RequestBody Hospede dadosAtualizados) {
        try {
            Optional<Hospede> hospedeAtualizado = hospedeService.atualizar(id, dadosAtualizados);
            return hospedeAtualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um hóspede
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        Optional<Hospede> hospede = hospedeService.buscarPorId(id); // Buscar pelo id, não pelo CPF

        if (hospede.isPresent()) {
            hospedeService.deletar(id); // Deletar pelo id
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
