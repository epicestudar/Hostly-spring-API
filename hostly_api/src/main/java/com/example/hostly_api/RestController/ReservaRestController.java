package com.example.hostly_api.RestController;

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
import java.util.List;
import java.time.LocalDate;
import com.example.hostly_api.Model.Hospede;
import com.example.hostly_api.Model.Quarto;
import com.example.hostly_api.Model.Reserva;
import com.example.hostly_api.Repository.HospedeRepository;
import com.example.hostly_api.Repository.QuartoRepository;
import com.example.hostly_api.Service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaRestController {
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    // Buscar reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable String id) {
        Optional<Reserva> reserva = reservaService.buscarPorId(id);
        return reserva.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hospede/{cpf}")
    public ResponseEntity<List<Reserva>> buscarReservasPorHospede(@PathVariable String cpf) {
        List<Reserva> reservas = reservaService.buscarPorCpfHospede(cpf);
        return ResponseEntity.ok(reservas);
    }

    @PostMapping
    public ResponseEntity<?> criarReserva(@RequestBody Reserva reserva) {
        // Lógica para salvar a reserva no banco de dados
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
