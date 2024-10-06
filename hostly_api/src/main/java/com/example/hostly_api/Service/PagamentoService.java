package com.example.hostly_api.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostly_api.Model.Pagamento;
import com.example.hostly_api.Repository.PagamentoRepository;


@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    // Buscar um pagamento por ID
    public Optional<Pagamento> buscarPorId(String id) {
        return pagamentoRepository.findById(id);
    }

    // Buscar um pagamento pelo ID da reserva
    public Optional<Pagamento> buscarPorReserva(String id) {
        return pagamentoRepository.findByReserva_Id(id);
    }

    // Salvar um novo pagamento
    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    // Atualizar as informações do pagamento
    public Optional<Pagamento> atualizar(String id, Pagamento dadosAtualizados) {
        Optional<Pagamento> pagamentoExistente = pagamentoRepository.findById(id);

        if (pagamentoExistente.isPresent()) {
            Pagamento pagamento = pagamentoExistente.get();
            // Atualizar os campos com os novos dados
            pagamento.setDataPagamento(dadosAtualizados.getDataPagamento());
            pagamento.setMetodoPagamento(dadosAtualizados.getMetodoPagamento());
            // Salvar as alterações
            return Optional.of(pagamentoRepository.save(pagamento));
        }

        throw new NoSuchElementException("Pagamento não encontrado com o ID: " + id);
    }

    // Deletar um pagamento
    public void deletar(String id) {
        pagamentoRepository.deleteById(id);
    }
}
