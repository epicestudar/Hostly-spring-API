package com.example.hostly_api.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostly_api.Model.Reserva;
import com.example.hostly_api.Repository.ReservaRepository;


@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    // Buscar reserva por ID
    public Optional<Reserva> buscarPorId(String id) {
        return this.reservaRepository.findById(id);
    }

    // Salvar uma nova reserva
    public Reserva salvar(Reserva reserva) {
        return this.reservaRepository.save(reserva);
    }

    // Atualizar as informações da reserva
    public Optional<Reserva> atualizar(String id, Reserva dadosAtualizados) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id);

        if (reservaExistente.isPresent()) {
            Reserva reserva = reservaExistente.get();
            // Atualizar os campos com os novos dados
            reserva.setDataCheckIn(dadosAtualizados.getDataCheckIn());
            reserva.setDataCheckOut(dadosAtualizados.getDataCheckOut());
            reserva.setQuantidadeDiarias(dadosAtualizados.getQuantidadeDiarias());
            reserva.setStatus(dadosAtualizados.getStatus());
            // Salvar as alterações
            return Optional.of(reservaRepository.save(reserva));
        }

        throw new NoSuchElementException("Reserva não encontrado com o ID: " + id);
    }

    // Deletar uma reserva
    public void deletar(String id) {
        this.reservaRepository.deleteById(id);
    }
}
