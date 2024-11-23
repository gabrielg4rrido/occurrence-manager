package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
        Long codigo,

        @NotBlank(message = "O logradouro é obrigatório")
        @Size(max = 255, message = "O logradouro deve ter no máximo 255 caracteres")
        String logradouro,

        @NotBlank(message = "O bairro é obrigatório")
        @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
        String bairro,

        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(
                regexp = "\\d{5}-\\d{3}",
                message = "O CEP deve estar no formato 00000-000"
        )
        String cep,

        @NotBlank(message = "A cidade é obrigatória")
        @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        @Size(min = 2, max = 2, message = "O estado deve conter exatamente 2 caracteres")
        String estado
) {
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
