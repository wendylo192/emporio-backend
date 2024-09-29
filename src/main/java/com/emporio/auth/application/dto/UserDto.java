package com.emporio.auth.application.dto;

import com.emporio.auth.infrastructure.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link com.emporio.auth.infrastructure.entity.User}
 */
@Value
public class UserDto implements Serializable {
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
    @Size(max = 120)
    @NotBlank
    String password;
    Set<Role> roles;
    boolean enabled;
}