package com.adgan.persitence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cattle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CattleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cattle", nullable = false)
    private int idCattle;

    @Column(nullable = false, length = 50)
    private String nombre;

    /*@Column(nullable = false, length = 50)
    private String madre;

    @Column(nullable = false, length = 50)
    private String padre;*/

    @Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fechaNacimiento;

    @Column(name = "id_owner", nullable = false)
    private int idOwner;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_owner", referencedColumnName = "id_owner", insertable = false, updatable = false)
    private OwnerEntity owner;
}
