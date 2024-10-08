package com.example.hostly_api.RestController;

import java.util.List;
import java.util.NoSuchElementException;
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


@RestController
@RequestMapping("/api/quartos")
public class QuartoRestController {
    @Autowired
    private QuartoService quartoService;

    // Buscar quarto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Quarto> buscarPorId(@PathVariable String id) {
        Optional<Quarto> quarto = quartoService.buscarPorId(id);
        
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

    @GetMapping
    public ResponseEntity<List<Quarto>> listarQuartos() {
        List<Quarto> quartos = quartoService.listarTodos();
        return ResponseEntity.ok(quartos);
    }

    // Salvar um novo quarto
    @PostMapping
    public ResponseEntity<Quarto> salvar(@RequestBody Quarto quarto) {
        Quarto novoQuarto = quartoService.salvar(quarto);
        return ResponseEntity.ok(novoQuarto);
    }

    // Atualizar um quarto
    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizar(@PathVariable String id, @RequestBody Quarto dadosAtualizados) {
        try {
            Optional<Quarto> quartoAtualizado = quartoService.atualizar(id, dadosAtualizados);
            return quartoAtualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

// Atualizar o quarto pelo código do quarto
@PutMapping("/codigo/{codigo_quarto}")
public ResponseEntity<Quarto> atualizarPorCodigo(@PathVariable String codigo_quarto, @RequestBody Quarto dadosAtualizados) {
    try {
        // Buscar o quarto pelo código
        Optional<Quarto> quartoExistente = quartoService.buscarPorCodigo(codigo_quarto);
        
        if (quartoExistente.isPresent()) {
            // Atualizar o quarto encontrado com os novos dados
            Optional<Quarto> quartoAtualizado = quartoService.atualizar(quartoExistente.get().getId(), dadosAtualizados);
            return quartoAtualizado.map(ResponseEntity::ok)
                                   .orElse(ResponseEntity.notFound().build());
        } else {
            // Caso o quarto não seja encontrado, retornar NOT FOUND
            return ResponseEntity.notFound().build();
        }
    } catch (NoSuchElementException e) {
        // Em caso de erro ou exceção, retornar NOT FOUND
        return ResponseEntity.notFound().build();
    }
}



    // Deletar um quarto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        Optional<Quarto> quarto = quartoService.buscarPorId(id);
        
        if (quarto.isPresent()) {
            quartoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
