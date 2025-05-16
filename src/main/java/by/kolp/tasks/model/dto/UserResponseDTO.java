package by.kolp.tasks.model.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(Long id, String username, String email) {
}
