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

import com.example.hostly_api.Model.Reserva;
import com.example.hostly_api.Service.ReservaService;


@RestController
@RequestMapping("/api/reservas")
public class ReservaRestController {
    @Autowired
    private ReservaService reservaService;

    // Buscar reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable String id) {
        Optional<Reserva> reserva = reservaService.buscarPorId(id);
        return reserva.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar uma nova reserva
    @PostMapping
    public ResponseEntity<Reserva> salvar(@RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.salvar(reserva);
        return ResponseEntity.ok(novaReserva);
    }

    // Atualizar uma reserva
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizar(@PathVariable String id, @RequestBody Reserva dadosAtualizados) {
        try {
            Optional<Reserva> reservaAtualizada = reservaService.atualizar(id, dadosAtualizados);
            return reservaAtualizada.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar uma reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        Optional<Reserva> reserva = reservaService.buscarPorId(id);
        
        if (reserva.isPresent()) {
            reservaService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
