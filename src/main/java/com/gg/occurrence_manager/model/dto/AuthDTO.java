package com.gg.occurrence_manager.model.dto;

import jakarta.validation.constraints.NotEmpty;

public record AuthDTO(
        @NotEmpty(message = "O login é obrigatório")
        String login,

        @NotEmpty(message = "A senha é obrigatória")
        String password
) {}