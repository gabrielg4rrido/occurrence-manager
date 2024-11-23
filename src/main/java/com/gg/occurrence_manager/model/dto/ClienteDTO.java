package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.Cliente;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import java.time.LocalDate;

public record ClienteDTO(
        Long codigo,

        @NotBlank(message = "O nome do cliente é obrigatório")
        @Size(max = 100, message = "O nome do cliente deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve ser uma data no passado")
        LocalDate dataNascimento,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(
                regexp = "\\d{11}",
                message = "O CPF deve conter exatamente 11 dígitos numéricos"
        )
        String cpf
) {
    public ClienteDTO(Cliente cliente) {
        this(
                cliente.getCodigo(),
                cliente.getNome(),
                cliente.getDataNascimento(),
                cliente.getCpf()
        );
    }
}