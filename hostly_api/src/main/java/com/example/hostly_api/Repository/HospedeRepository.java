package com.example.hostly_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.hostly_api.Model.Hospede;


@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long>{
    Optional<Hospede> findByEmail(String email);
    Optional<Hospede> findByCpf(String cpf);
}
