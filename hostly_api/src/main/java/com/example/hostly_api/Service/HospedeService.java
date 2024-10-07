package com.example.hostly_api.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostly_api.Model.Hospede;
import com.example.hostly_api.Repository.HospedeRepository;

@Service
public class HospedeService {
    @Autowired
    private HospedeRepository hospedeRepository;

    public List<Hospede> listarTodos() {
    return hospedeRepository.findAll(); // Busca todos os hóspedes no banco de dados
}
    // Buscar hóspede por CPF
    public Optional<Hospede> buscarPorCpf(String cpf) {
        return hospedeRepository.findByCpf(cpf);
    }

    // Buscar hóspede por email
    public Optional<Hospede> buscarPorEmail(String email) {
        return hospedeRepository.findByEmail(email);
    }

    public Optional<Hospede> buscarPorId(String id) {
        return hospedeRepository.findById(id);  // Busca pelo ID
    }

    // Salvar um novo hóspede
    public Hospede salvar(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }

    // Atualizar um hóspede
    public Optional<Hospede> atualizar(String id, Hospede dadosAtualizados) {
        Optional<Hospede> hospedeExistente = hospedeRepository.findById(id);

        if (hospedeExistente.isPresent()) {
            Hospede hospede = hospedeExistente.get();
            // Atualizar os campos com os novos dados
            hospede.setNome(dadosAtualizados.getNome());
            hospede.setDataNascimento(dadosAtualizados.getDataNascimento());
            hospede.setTelefone(dadosAtualizados.getTelefone());
            hospede.setEmail(dadosAtualizados.getEmail());
            hospede.setCpf(dadosAtualizados.getCpf());
            hospede.setSenha(dadosAtualizados.getSenha());
            // Salvar as alterações
            return Optional.of(hospedeRepository.save(hospede));
        }

        throw new NoSuchElementException("Hóspede não encontrado com o ID: " + id);
    }

    // Deletar um hóspede
    public void deletar(String id) {
        hospedeRepository.deleteById(id);
    }
}
