package com.example.hostly_api.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.hostly_api.Model.Hospede;


@Repository
public interface HospedeRepository extends MongoRepository<Hospede, String>{
    Optional<Hospede> findById(String id);  // Buscar pelo ID
    Optional<Hospede> findByEmail(String email);
    Optional<Hospede> findByCpf(String cpf);
}
