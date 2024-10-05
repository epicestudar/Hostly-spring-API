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

import com.example.hostly_api.Model.Quarto;
import com.example.hostly_api.Service.QuartoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/quartos")
public class QuartoRestController {
    @Autowired
    private QuartoService quartoService;

    // Buscar quarto por ID
    @GetMapping("/{id_quarto}")
    public ResponseEntity<Quarto> buscarPorId(@PathVariable Long id_quarto) {
        Optional<Quarto> quarto = quartoService.buscarPorId(id_quarto);
        
        return quarto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar quarto pelo código do quarto
    @GetMapping("/codigo/{codigo_quarto}")
    public ResponseEntity<Quarto> buscarPorCodigo(@PathVariable String codigo_quarto) {
        Optional<Quarto> quarto = quartoService.buscarPorCodigo(codigo_quarto);
        
        return quarto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar um novo quarto
    @PostMapping
    public ResponseEntity<Quarto> salvar(@RequestBody Quarto quarto) {
        Quarto novoQuarto = quartoService.salvar(quarto);
        return ResponseEntity.ok(novoQuarto);
    }

    // Atualizar um quarto
    @PutMapping("/{id_quarto}")
    public ResponseEntity<Quarto> atualizar(@PathVariable Long id_quarto, @RequestBody Quarto dadosAtualizados) {
        try {
            Optional<Quarto> quartoAtualizado = quartoService.atualizar(id_quarto, dadosAtualizados);
            return quartoAtualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um quarto
    @DeleteMapping("/{id_quarto}")
    public ResponseEntity<Void> deletar(@PathVariable Long id_quarto) {
        Optional<Quarto> quarto = quartoService.buscarPorId(id_quarto);
        
        if (quarto.isPresent()) {
            quartoService.deletar(id_quarto);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
