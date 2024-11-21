package com.gg.occurrence_manager.repository;

import com.gg.occurrence_manager.model.FotoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoOcorrenciaRepository extends JpaRepository<FotoOcorrencia, Long> {
}
