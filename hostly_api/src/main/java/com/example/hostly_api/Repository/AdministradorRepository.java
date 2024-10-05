package com.example.hostly_api.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.hostly_api.Model.Administrador;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, String>{
    Optional<Administrador> findByEmail(String email); // estou usando o optional pois torna o código mais seguro contra NullPointerException e melhora a tratativa de erros.
}
