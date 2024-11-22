package com.gg.occurrence_manager.repository;

import com.gg.occurrence_manager.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNomeAndCpf(String nome, String cpf);
}