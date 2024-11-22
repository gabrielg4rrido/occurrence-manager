package com.gg.occurrence_manager.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record CadastroOcorrenciaDTO(
        EnderecoDTO endereco,
        ClienteDTO cliente,
        LocalDate dataOcorrencia,
        List<MultipartFile> evidencias
) {}