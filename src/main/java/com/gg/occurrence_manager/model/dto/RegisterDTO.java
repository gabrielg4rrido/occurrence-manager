package com.gg.occurrence_manager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "O login é obrigatório")
        @Size(min = 5, max = 20, message = "O login deve ter entre 5 e 20 caracteres")
        String login,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
        String password
) {}
