package com.example.hostly_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hostly_api.Model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
