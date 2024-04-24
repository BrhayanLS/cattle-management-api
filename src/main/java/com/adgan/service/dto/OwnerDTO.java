package com.adgan.service.dto;

import com.adgan.persitence.entity.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {
    private Integer idOwner;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String username;

    @NotBlank
    private String contacto;

    @NotBlank
    @Email
    private String correo;

    @NotBlank
    private String password;

    private String role;
}
