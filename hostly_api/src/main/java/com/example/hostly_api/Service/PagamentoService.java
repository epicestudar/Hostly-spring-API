package com.example.hostly_api.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostly_api.Model.Pagamento;
import com.example.hostly_api.Repository.PagamentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    // Buscar um pagamento por ID
    public Optional<Pagamento> buscarPorId(String id_pagamento) {
        return pagamentoRepository.findById(id_pagamento);
    }

    // Buscar um pagamento pelo ID da reserva
    public Optional<Pagamento> buscarPorReserva(String id_reserva) {
        return pagamentoRepository.findByReserva_IdReserva(id_reserva);
    }

    // Salvar um novo pagamento
    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    // Atualizar as informações do pagamento
    public Optional<Pagamento> atualizar(String id_pagamento, Pagamento dadosAtualizados) {
        Optional<Pagamento> pagamentoExistente = pagamentoRepository.findById(id_pagamento);

        if (pagamentoExistente.isPresent()) {
            Pagamento pagamento = pagamentoExistente.get();
            // Atualizar os campos com os novos dados
            pagamento.setData_pagamento(dadosAtualizados.getData_pagamento());
            pagamento.setMetodo_pagamento(dadosAtualizados.getMetodo_pagamento());
            // Salvar as alterações
            return Optional.of(pagamentoRepository.save(pagamento));
        }

        throw new EntityNotFoundException("Pagamento não encontrado com o ID: " + id_pagamento);
    }

    // Deletar um pagamento
    public void deletar(String id_pagamento) {
        pagamentoRepository.deleteById(id_pagamento);
    }
}
