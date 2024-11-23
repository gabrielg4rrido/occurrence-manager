package com.gg.occurrence_manager.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastroOcorrenciaDTO(
        @NotNull(message = "O endereço é obrigatório")
        @Valid
        EnderecoDTO endereco,

        @NotNull(message = "O cliente é obrigatório")
        @Valid
        ClienteDTO cliente,

        @NotNull(message = "A data da ocorrência é obrigatória")
        LocalDate dataOcorrencia
) {}