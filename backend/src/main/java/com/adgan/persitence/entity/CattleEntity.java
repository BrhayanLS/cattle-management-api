package com.adgan.persitence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entidad que representa un animal de ganado en el sistema.
 * Esta clase mapea la tabla 'cattle' en la base de datos y contiene
 * la información de cada animal, incluyendo su estado, datos básicos
 * y relaciones con propietarios y ventas.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "cattle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CattleEntity {

    /**
     * Identificador único del animal.
     * Se genera automáticamente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cattle", nullable = false)
    private Integer idCattle;

    /**
     * Estado del animal en el sistema.
     * true = activo, false = inactivo
     */
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean estado;

    /**
     * Nombre o identificación del animal.
     */
    @Column(nullable = false, length = 50)
    private String nombre;

    /*@Column(nullable = false, length = 50)
    private String madre;

    @Column(nullable = false, length = 50)
    private String padre;*/

    /**
     * Fecha de nacimiento del animal.
     */
    @Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    /**
     * ID del propietario al que pertenece el animal.
     */
    @Column(name = "id_owner", nullable = false)
    private Integer idOwner;

    /**
     * Relación con el propietario del animal.
     * Carga perezosa (LAZY) para optimizar el rendimiento.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_owner", referencedColumnName = "id_owner", insertable = false, updatable = false)
    private OwnerEntity owner;

    /**
     * Relación con la venta del animal, si existe.
     * Relación uno a uno con SaleCattleEntity.
     */
    @OneToOne(mappedBy = "cattle")
    private SaleCattleEntity saleCattle;
}
