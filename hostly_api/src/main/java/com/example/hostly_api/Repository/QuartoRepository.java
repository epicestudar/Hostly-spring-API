package com.example.hostly_api.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.hostly_api.Model.Quarto;

@Repository
public interface QuartoRepository extends MongoRepository<Quarto, String> {
    Optional<Quarto> findByCodigoQuarto(String codigoQuarto);
}
