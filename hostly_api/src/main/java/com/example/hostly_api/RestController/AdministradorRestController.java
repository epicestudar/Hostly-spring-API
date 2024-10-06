package com.example.hostly_api.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostly_api.Model.Administrador;
import com.example.hostly_api.Service.AdministradorService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/administrador")
public class AdministradorRestController {
    @Autowired
    private AdministradorService administradorService;

    // Buscar administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscarPorId(@PathVariable String id) {
        Optional<Administrador> administrador = administradorService.buscarPorId(id);
        
        return administrador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar administrador por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Administrador> buscarPorEmail(@PathVariable String email) {
        Optional<Administrador> administrador = administradorService.buscarPorEmail(email);
        
        return administrador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar um administrador
    @PostMapping
    public ResponseEntity<Administrador> salvar(@RequestBody Administrador administrador) {
        Administrador novoAdministrador = administradorService.salvar(administrador);
        return ResponseEntity.ok(novoAdministrador);
    }

    // Atualizar um administrador
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> atualizar(@PathVariable String id, @RequestBody Administrador dadosAtualizados) {
        try {
            Optional<Administrador> administradorAtualizado = administradorService.atualizar(id, dadosAtualizados);
            return administradorAtualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        Optional<Administrador> administrador = administradorService.buscarPorId(id);
        
        if (administrador.isPresent()) {
            administradorService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
