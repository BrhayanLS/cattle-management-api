package com.adgan.service.dto;

import com.adgan.persitence.entity.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO (Data Transfer Object) para la transferencia de datos de propietarios.
 * Este objeto se utiliza para validar y transferir datos entre la capa de controlador
 * y la capa de servicio.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {
    /**
     * Identificador único del propietario
     */
    private Integer idOwner;

    /**
     * Nombre del propietario
     * No puede estar vacío
     */
    @NotBlank
    private String nombre;

    /**
     * Apellido del propietario
     * No puede estar vacío
     */
    @NotBlank
    private String apellido;

    /**
     * Nombre de usuario para el sistema
     * No puede estar vacío
     */
    @NotBlank
    private String username;

    /**
     * Número de contacto del propietario
     * No puede estar vacío
     */
    @NotBlank
    private String contacto;

    /**
     * Correo electrónico del propietario
     * Debe ser un correo válido y no puede estar vacío
     */
    @NotBlank
    @Email
    private String correo;

    /**
     * Contraseña del usuario
     * No puede estar vacía
     */
    @NotBlank
    private String password;

    /**
     * ID del rol asignado al propietario
     */
    private Integer roleId;
}
