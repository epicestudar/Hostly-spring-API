package com.example.hostly_api.RestController;

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

import com.example.hostly_api.Model.Pagamento;
import com.example.hostly_api.Service.PagamentoService;


@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoRestController {
    @Autowired
    private PagamentoService pagamentoService;

    // Buscar pagamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable String id) {
        Optional<Pagamento> pagamento = pagamentoService.buscarPorId(id);
        
        return pagamento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar pagamento pelo ID da reserva
    @GetMapping("/reserva/{id_reserva}")
    public ResponseEntity<Pagamento> buscarPorReserva(@PathVariable String id_reserva) {
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
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(@PathVariable String id, @RequestBody Pagamento dadosAtualizados) {
        try {
            Optional<Pagamento> pagamentoAtualizado = pagamentoService.atualizar(id, dadosAtualizados);
            return pagamentoAtualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        Optional<Pagamento> pagamento = pagamentoService.buscarPorId(id);
        
        if (pagamento.isPresent()) {
            pagamentoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
