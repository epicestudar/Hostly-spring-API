package com.example.hostly_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hostly_api.Model.Quarto;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long>{

}
