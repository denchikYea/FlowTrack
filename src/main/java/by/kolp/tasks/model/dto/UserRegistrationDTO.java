package by.kolp.tasks.model.dto;

import lombok.Builder;

@Builder
public record UserRegistrationDTO(String username, String email, String password) {
}
