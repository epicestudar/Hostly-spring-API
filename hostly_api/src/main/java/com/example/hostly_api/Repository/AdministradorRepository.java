package com.example.hostly_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hostly_api.Model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
Administrador findByIdAdministrador (Long id);
Administrador findByEmail(String email);
}
