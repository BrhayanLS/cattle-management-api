package com.adgan.persitence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * Entidad que representa a un propietario en el sistema.
 * Esta clase mapea la tabla 'owner' en la base de datos y contiene
 * la información personal y de autenticación de los propietarios.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "owner")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerEntity {

    /**
     * Identificador único del propietario.
     * Se genera automáticamente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner", nullable = false)
    private Integer idOwner;

    /**
     * Estado del propietario en el sistema.
     * true = activo, false = inactivo
     */
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean estado;

    /**
     * Nombre del propietario.
     * No puede estar vacío.
     */
    @Column(nullable = false, length = 50)
    @NotBlank
    private String nombre;

    /**
     * Apellido del propietario.
     * No puede estar vacío.
     */
    @Column(nullable = false, length = 50)
    @NotBlank
    private String apellido;

    /**
     * Nombre de usuario para autenticación.
     * No puede estar vacío.
     */
    @Column(nullable = false, length = 50)
    @NotBlank
    private String username;

    /**
     * Número de contacto del propietario.
     * Debe ser único en el sistema.
     */
    @Column(nullable = false, length = 15, unique = true)
    @NotBlank
    private String contacto;

    /**
     * Correo electrónico del propietario.
     * Debe ser único y tener formato válido de email.
     */
    @Column(nullable = false, length = 75, unique = true)
    @NotBlank
    @Email
    private String correo;

    /**
     * Contraseña del propietario.
     * No puede estar vacía.
     */
    @Column(nullable = false, length = 200)
    @NotBlank
    private String password;

    /**
     * Lista de ganado asociado al propietario.
     * Relación uno a muchos con CattleEntity.
     */
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CattleEntity> cattle;

    /*@ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "owner_roles", joinColumns = @JoinColumn(name = "owner_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private RoleEntity role;*/

    /**
     * Rol asignado al propietario.
     * Relación muchos a uno con RoleEntity.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
}
