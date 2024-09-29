package com.emporio.auth.api.response;

import com.emporio.auth.infrastructure.entity.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.emporio.auth.application.dto.UserDto}
 */
@Value
public class UserResponseDto implements Serializable {
    Long id;
    @Size(max = 20)
    @NotBlank
    String username;
    @Size(max = 50)
    @NotBlank
    String firstname;
    @Size(max = 50)
    @NotBlank
    String lastname;
    @Size(max = 50)
    @Email
    @NotBlank
    String email;
    Set<ERole> roleNames;
}