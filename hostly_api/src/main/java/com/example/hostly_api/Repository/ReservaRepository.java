package com.example.hostly_api.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.hostly_api.Model.Reserva;

@Repository
public interface ReservaRepository extends MongoRepository<Reserva, String> {
    // Buscar reservas pelo CPF do hóspede
    List<Reserva> findByHospedeCpf(String cpf);
}

