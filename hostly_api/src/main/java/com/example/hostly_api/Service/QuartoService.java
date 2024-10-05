package com.example.hostly_api.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import com.example.hostly_api.Model.Quarto;
import com.example.hostly_api.Repository.QuartoRepository;

@Service
public class QuartoService {
    @Autowired
    private QuartoRepository quartoRepository;

    // Buscar quarto por ID
    public Optional<Quarto> buscarPorId(String id_quarto) {
        return quartoRepository.findById(id_quarto);
    }

    // Buscar quarto pelo código do quarto
    public Optional<Quarto> buscarPorCodigo(String codigo_quarto) {
        return quartoRepository.findByCodigoQuarto(codigo_quarto);
    }

    // Salvar um novo quarto
    public Quarto salvar(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    // Atualizar as informações do quarto
    public Optional<Quarto> atualizar(String id_quarto, Quarto dadosAtualizados) {
        Optional<Quarto> quartoExistente = quartoRepository.findById(id_quarto);

        if (quartoExistente.isPresent()) {
            Quarto quarto = quartoExistente.get();
            // Atualizar os campos com os novos dados
            quarto.setCodigo_quarto(dadosAtualizados.getCodigo_quarto());
            quarto.setTipo_quarto(dadosAtualizados.getTipo_quarto());
            quarto.setCapacidade_quarto(dadosAtualizados.getCapacidade_quarto());
            quarto.setValor_quarto(dadosAtualizados.getValor_quarto());
            quarto.setStatus(dadosAtualizados.getStatus());
            // Salvar as alterações
            return Optional.of(quartoRepository.save(quarto));
        }

        throw new EntityNotFoundException("Quarto não encontrado com o ID: " + id_quarto);
    }

    // Deletar um quarto
    public void deletar(String id_quarto) {
        quartoRepository.deleteById(id_quarto);
    }
}
