package com.example.hostly_api.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.hostly_api.Model.Pagamento;

@Repository
public interface PagamentoRepository extends MongoRepository<Pagamento, String> {
Optional<Pagamento> findByReserva_IdReserva(String id_reserva);
}
