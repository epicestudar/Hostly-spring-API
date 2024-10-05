package com.example.hostly_api.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.persistence.EntityNotFoundException;

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

    // Salvar um novo hóspede
    @PostMapping
    public ResponseEntity<Hospede> salvar(@RequestBody Hospede hospede) {
        Hospede novoHospede = hospedeService.salvar(hospede);
        return ResponseEntity.ok(novoHospede);
    }

     // Atualizar um hóspede
    @PutMapping("/{id_hospede}")
    public ResponseEntity<Hospede> atualizar(@PathVariable String id_hospede, @RequestBody Hospede dadosAtualizados) {
        try {
            Optional<Hospede> hospedeAtualizado = hospedeService.atualizar(id_hospede, dadosAtualizados);
            return hospedeAtualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um hóspede
    @DeleteMapping("/{id_hospede}")
    public ResponseEntity<Void> deletar(@PathVariable String id_hospede) {
        Optional<Hospede> hospede = hospedeService.buscarPorCpf(id_hospede.toString());
        
        if (hospede.isPresent()) {
            hospedeService.deletar(id_hospede);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
