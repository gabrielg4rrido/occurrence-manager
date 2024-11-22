package com.gg.occurrence_manager.repository;

import com.gg.occurrence_manager.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByCepAndLogradouroAndBairroAndCidadeAndEstado(
            String cep,
            String logradouro,
            String bairro,
            String cidade,
            String estado
    );
}