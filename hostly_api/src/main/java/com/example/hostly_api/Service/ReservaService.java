package com.example.hostly_api.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostly_api.Model.Reserva;
import com.example.hostly_api.Repository.ReservaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    // Buscar reserva por ID
    public Optional<Reserva> buscarPorId(String id_reserva) {
        return this.reservaRepository.findById(id_reserva);
    }

    // Salvar uma nova reserva
    public Reserva salvar(Reserva reserva) {
        return this.reservaRepository.save(reserva);
    }

    // Atualizar as informações da reserva
    public Optional<Reserva> atualizar(String id_reserva, Reserva dadosAtualizados) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id_reserva);

        if (reservaExistente.isPresent()) {
            Reserva reserva = reservaExistente.get();
            // Atualizar os campos com os novos dados
            reserva.setData_check_in(dadosAtualizados.getData_check_in());
            reserva.setData_check_out(dadosAtualizados.getData_check_out());
            reserva.setQuantidade_diarias(dadosAtualizados.getQuantidade_diarias());
            reserva.setStatus(dadosAtualizados.getStatus());
            // Salvar as alterações
            return Optional.of(reservaRepository.save(reserva));
        }

        throw new EntityNotFoundException("Reserva não encontrado com o ID: " + id_reserva);
    }

    // Deletar uma reserva
    public void deletar(String id_reserva) {
        this.reservaRepository.deleteById(id_reserva);
    }
}
