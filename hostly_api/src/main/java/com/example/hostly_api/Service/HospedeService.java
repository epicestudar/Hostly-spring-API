package com.example.hostly_api.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostly_api.Model.Hospede;
import com.example.hostly_api.Repository.HospedeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HospedeService {
    @Autowired
    private HospedeRepository hospedeRepository;

    // Buscar hóspede por CPF
    public Optional<Hospede> buscarPorCpf(String cpf) {
        return hospedeRepository.findByCpf(cpf);
    }

    // Buscar hóspede por email
    public Optional<Hospede> buscarPorEmail(String email) {
        return hospedeRepository.findByEmail(email);
    }

    // Salvar um novo hóspede
    public Hospede salvar(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }

    // Atualizar um hóspede
    public Optional<Hospede> atualizar(String id_hospede, Hospede dadosAtualizados) {
        Optional<Hospede> hospedeExistente = hospedeRepository.findById(id_hospede);

        if (hospedeExistente.isPresent()) {
            Hospede hospede = hospedeExistente.get();
            // Atualizar os campos com os novos dados
            hospede.setNome(dadosAtualizados.getNome());
            hospede.setData_nascimento(dadosAtualizados.getData_nascimento());
            hospede.setTelefone(dadosAtualizados.getTelefone());
            hospede.setEmail(dadosAtualizados.getEmail());
            hospede.setCpf(dadosAtualizados.getCpf());
            hospede.setSenha(dadosAtualizados.getSenha());
            // Salvar as alterações
            return Optional.of(hospedeRepository.save(hospede));
        }

        throw new EntityNotFoundException("Hóspede não encontrado com o ID: " + id_hospede);
    }

    // Deletar um hóspede
    public void deletar(String id_hospede) {
        hospedeRepository.deleteById(id_hospede);
    }
}
