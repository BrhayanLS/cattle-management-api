package com.adgan.persitence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un rol en el sistema.
 * Esta clase mapea la tabla 'roles' en la base de datos y define
 * los diferentes roles que pueden tener los usuarios del sistema.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class RoleEntity {
    /**
     * Identificador único del rol.
     * Se genera automáticamente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del rol.
     * Utiliza el enum ERole para definir los tipos de roles disponibles.
     */
    @Enumerated(EnumType.STRING)
    private ERole name;
}