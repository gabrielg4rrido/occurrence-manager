package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.FotoOcorrencia;

import java.time.LocalDate;

public record FotoOcorrenciaDTO(String pathBucket, String hash, LocalDate dataCriacao) {
    public FotoOcorrenciaDTO(FotoOcorrencia foto) {
        this(foto.getPathBucket(), foto.getHash(), foto.getDataCriacao());
    }
}