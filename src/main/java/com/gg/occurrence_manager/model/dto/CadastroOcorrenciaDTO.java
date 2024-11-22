package com.gg.occurrence_manager.model.dto;

import java.time.LocalDate;

public record CadastroOcorrenciaDTO(
        EnderecoDTO endereco,
        ClienteDTO cliente,
        LocalDate dataOcorrencia
) {}