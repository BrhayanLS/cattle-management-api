package com.adgan.persitence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale", nullable = false)
    private Integer idSale;

    @Column(name = "id_owner", nullable = false)
    private Integer idOwner;

    @Column(name = "id_cattle", nullable = false, unique = true)
    private Integer idCattle;

    @Column(name = "fecha_venta", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fechaVenta;

    @Column(nullable = false)
    private Double peso;

    @Column(name = "precio_kilo", nullable = false)
    private Integer precioKilo;

    @Column(name = "valor_camion", nullable = false)
    private Integer valorCamion;

    @Column(name = "valor_bascula", nullable = false)
    private Integer valorBascula;

    @Column(name = "total_neto", nullable = false)
    private Double totalNeto;

    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_owner", referencedColumnName = "id_owner", insertable = false, updatable = false)
    private OwnerEntity owner;

    @OneToOne
    @JoinColumn(name = "id_cattle", referencedColumnName = "id_cattle", insertable = false, updatable = false)
    private CattleEntity cattle;
}
