package com.gg.occurrence_manager.model.dto;

import java.time.LocalDate;

public record ClienteDTO(Long codigo, String nome, LocalDate dataNascimento, String cpf) {
}
