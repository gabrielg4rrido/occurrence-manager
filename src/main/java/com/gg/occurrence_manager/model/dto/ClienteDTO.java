package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.Cliente;

import java.time.LocalDate;

public record ClienteDTO(Long codigo, String nome, LocalDate dataNascimento, String cpf) {
    public ClienteDTO(Cliente cliente) {
        this(
                cliente.getCodigo(),
                cliente.getNome(),
                cliente.getDataNascimento(),
                cliente.getCpf()
        );
    }
}
