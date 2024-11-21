package com.gg.occurrence_manager.repository;

import com.gg.occurrence_manager.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
}