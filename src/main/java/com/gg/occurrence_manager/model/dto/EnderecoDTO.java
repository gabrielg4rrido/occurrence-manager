package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.Endereco;

public record EnderecoDTO(Long codigo, String logradouro, String bairro, String cep, String cidade, String estado) {

    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getCodigo(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }
}
