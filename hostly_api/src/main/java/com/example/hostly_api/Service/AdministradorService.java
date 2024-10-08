package com.example.hostly_api.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostly_api.Model.Administrador;
import com.example.hostly_api.Repository.AdministradorRepository;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    
    // Buscar administrador por ID
    public Optional<Administrador> buscarPorId(String id) {
        return administradorRepository.findById(id);
    }

    // Buscar administrador por email
    public Optional<Administrador> buscarPorEmail(String email) {
        return administradorRepository.findByEmail(email);
    }


    // Salvar um administrador
    public Administrador salvar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

   // Atualizar um administrador
   public Optional<Administrador> atualizar(String id, Administrador dadosAtualizados) {
    Optional<Administrador> administradorExistente = administradorRepository.findById(id);

    if (administradorExistente.isPresent()) {
        Administrador admin = administradorExistente.get();
        // Atualizar os campos com os novos dados
        admin.setEmail(dadosAtualizados.getEmail());
        admin.setSenha(dadosAtualizados.getSenha());
        // Salvar as alterações
        return Optional.of(administradorRepository.save(admin));
    }

    throw new NoSuchElementException("Administrador não encontrado com o ID: " + id);
}

    // Deletar um administrador
    public void deletar(String id) {
        administradorRepository.deleteById(id);
    }
}
