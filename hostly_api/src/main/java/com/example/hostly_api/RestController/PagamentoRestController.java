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

import com.example.hostly_api.Model.Pagamento;
import com.example.hostly_api.Service.PagamentoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoRestController {
    @Autowired
    private PagamentoService pagamentoService;

    // Buscar pagamento por ID
    @GetMapping("/{id_pagamento}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id_pagamento) {
        Optional<Pagamento> pagamento = pagamentoService.buscarPorId(id_pagamento);
        
        return pagamento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar pagamento pelo ID da reserva
    @GetMapping("/reserva/{id_reserva}")
    public ResponseEntity<Pagamento> buscarPorReserva(@PathVariable Long id_reserva) {
        Optional<Pagamento> pagamento = pagamentoService.buscarPorReserva(id_reserva);
        
        return pagamento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar um novo pagamento
     @PostMapping
    public ResponseEntity<Pagamento> salvar(@RequestBody Pagamento pagamento) {
        Pagamento novoPagamento = pagamentoService.salvar(pagamento);
        return ResponseEntity.ok(novoPagamento);
    }

    // Atualizar um pagamento
    @PutMapping("/{id_pagamento}")
    public ResponseEntity<Pagamento> atualizar(@PathVariable Long id_pagamento, @RequestBody Pagamento dadosAtualizados) {
        try {
            Optional<Pagamento> pagamentoAtualizado = pagamentoService.atualizar(id_pagamento, dadosAtualizados);
            return pagamentoAtualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um pagamento
    @DeleteMapping("/{id_pagamento}")
    public ResponseEntity<Void> deletar(@PathVariable Long id_pagamento) {
        Optional<Pagamento> pagamento = pagamentoService.buscarPorId(id_pagamento);
        
        if (pagamento.isPresent()) {
            pagamentoService.deletar(id_pagamento);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
