package com.adgan.persitence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "owner")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner", nullable = false)
    private Integer idOwner;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean estado;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String nombre;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String apellido;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String username;

    @Column(nullable = false, length = 15, unique = true)
    @NotBlank
    private String contacto;

    @Column(nullable = false, length = 75, unique = true)
    @NotBlank
    @Email
    private String correo;

    @Column(nullable = false, length = 200)
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CattleEntity> cattle;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "owner_roles", joinColumns = @JoinColumn(name = "owner_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
}
