package com.example.hostly_api.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hostly_api.Model.Quarto;
import com.example.hostly_api.Repository.QuartoRepository;

@Service
public class QuartoService {
    @Autowired
    private QuartoRepository quartoRepository;

    // Buscar quarto por ID
    public Optional<Quarto> buscarPorId(String id) {
        return quartoRepository.findById(id);
    }

    // Buscar quarto pelo código do quarto
    public Optional<Quarto> buscarPorCodigo(String codigoQuarto) {
        return quartoRepository.findByCodigoQuarto(codigoQuarto);
    }

    public List<Quarto> listarTodos() {
        return quartoRepository.findAll();
    }

    // Salvar um novo quarto
    public Quarto salvar(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    // Atualizar as informações do quarto
    public Optional<Quarto> atualizar(String id, Quarto dadosAtualizados) {
        Optional<Quarto> quartoExistente = quartoRepository.findById(id);

        if (quartoExistente.isPresent()) {
            Quarto quarto = quartoExistente.get();
            // Atualizar os campos com os novos dados
            quarto.setCodigoQuarto(dadosAtualizados.getCodigoQuarto());
            quarto.setTipoQuarto(dadosAtualizados.getTipoQuarto());
            quarto.setCapacidadeQuarto(dadosAtualizados.getCapacidadeQuarto());
            quarto.setValorQuarto(dadosAtualizados.getValorQuarto());
            quarto.setStatus(dadosAtualizados.getStatus());
            // Salvar as alterações
            return Optional.of(quartoRepository.save(quarto));
        }

        throw new NoSuchElementException("Quarto não encontrado com o ID: " + id);
    }

    // Deletar um quarto
    public void deletar(String id) {
        quartoRepository.deleteById(id);
    }
}
