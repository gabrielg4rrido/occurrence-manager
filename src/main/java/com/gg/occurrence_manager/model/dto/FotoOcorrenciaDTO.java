package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.FotoOcorrencia;

public record FotoOcorrenciaDTO(String pathBucket, String hash) {
    public FotoOcorrenciaDTO(FotoOcorrencia foto) {
        this(foto.getPathBucket(), foto.getHash());
    }
}