package com.adgan.persitence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private Integer idCattle;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean estado;


    @Column(nullable = false, length = 50)
    private String nombre;

    /*@Column(nullable = false, length = 50)
    private String madre;

    @Column(nullable = false, length = 50)
    private String padre;*/

    @Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    @Column(name = "id_owner", nullable = false)
    private Integer idOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_owner", referencedColumnName = "id_owner", insertable = false, updatable = false)
    private OwnerEntity owner;

    @OneToOne(mappedBy = "cattle")
    private SaleCattleEntity saleCattle;
}
