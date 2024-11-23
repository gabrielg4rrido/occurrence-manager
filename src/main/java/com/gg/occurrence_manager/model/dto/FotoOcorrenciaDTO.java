package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.FotoOcorrencia;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FotoOcorrenciaDTO(
        @NotBlank(message = "O caminho do bucket é obrigatório")
        String pathBucket,

        @NotBlank(message = "O hash é obrigatório")
        String hash,

        @NotNull(message = "A data de criação é obrigatória")
        LocalDate dataCriacao
) {
    public FotoOcorrenciaDTO(FotoOcorrencia foto) {
        this(foto.getPathBucket(), foto.getHash(), foto.getDataCriacao());
    }
}
