package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.Ocorrencia;
import com.gg.occurrence_manager.model.enums.StatusOcorrencia;

import java.time.LocalDate;

public record OcorrenciaDTO(Long codigo, EnderecoDTO endereco, ClienteDTO cliente, LocalDate dataOcorrencia, StatusOcorrencia statusOcorrencia) {
    public OcorrenciaDTO(Ocorrencia ocorrencia) {
        this(
                ocorrencia.getCodigo(),
                new EnderecoDTO(ocorrencia.getEndereco()),
                new ClienteDTO(ocorrencia.getCliente()),
                ocorrencia.getDataOcorrencia(),
                StatusOcorrencia.ABERTA
        );
    }
}
