package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.Ocorrencia;

import java.time.LocalDate;
import java.util.List;

public record OcorrenciaDTO(Long codigo, EnderecoDTO endereco, ClienteDTO cliente, LocalDate dataOcorrencia, List<FotoOcorrenciaDTO> fotos) {
    public OcorrenciaDTO(Ocorrencia ocorrencia) {
        this(
                ocorrencia.getCodigo(),
                new EnderecoDTO(ocorrencia.getEndereco()),
                new ClienteDTO(ocorrencia.getCliente()),
                ocorrencia.getDataOcorrencia(),
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
                fotos
        );
    }
}
