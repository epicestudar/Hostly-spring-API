package com.example.hostly_api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hostly_api.Model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
Optional<Pagamento> findByReserva_IdReserva(Long id_reserva);
}
