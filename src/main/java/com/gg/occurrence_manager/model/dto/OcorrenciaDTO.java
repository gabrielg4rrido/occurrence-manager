package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.Ocorrencia;
import com.gg.occurrence_manager.model.enums.StatusOcorrencia;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public record OcorrenciaDTO(
        Long codigo,

        @NotNull(message = "O endereço é obrigatório")
        EnderecoDTO endereco,

        @NotNull(message = "O cliente é obrigatório")
        ClienteDTO cliente,

        @NotNull(message = "A data da ocorrência é obrigatória")
        LocalDate dataOcorrencia,

        @NotNull(message = "O status da ocorrência é obrigatório")
        StatusOcorrencia statusOcorrencia,

        @NotNull(message = "As fotos são obrigatórias")
        List<FotoOcorrenciaDTO> fotos
) {
    public OcorrenciaDTO(Ocorrencia ocorrencia) {
        this(
                ocorrencia.getCodigo(),
                new EnderecoDTO(ocorrencia.getEndereco()),
                new ClienteDTO(ocorrencia.getCliente()),
                ocorrencia.getDataOcorrencia(),
                ocorrencia.getStatusOcorrencia(),
                ocorrencia.getFotos().stream()
                        .map(FotoOcorrenciaDTO::new)
                        .toList()
        );
    }

    public OcorrenciaDTO(Ocorrencia ocorrencia, List<FotoOcorrenciaDTO> fotos) {
        this(
                ocorrencia.getCodigo(),
                new EnderecoDTO(ocorrencia.getEndereco()),
                new ClienteDTO(ocorrencia.getCliente()),
                ocorrencia.getDataOcorrencia(),
                ocorrencia.getStatusOcorrencia(),
                fotos
        );
    }
}