package com.gg.occurrence_manager.model.dto;

import com.gg.occurrence_manager.model.enums.Role;

public record RegisterDTO (String login, String password, Role role){
}
