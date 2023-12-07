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
    private int idSale;

    @Column(name = "id_owner", nullable = false)
    private int idOwner;

    @Column(name = "id_cattle", nullable = false, unique = true)
    private int idCattle;

    @Column(name = "fecha_venta", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fechaVenta;

    @Column(nullable = false)
    private double peso;

    @Column(name = "precio_kilo", nullable = false)
    private int precioKilo;

    @Column(name = "valor_camion", nullable = false)
    private int valorCamion;

    @Column(name = "valor_bascula", nullable = false)
    private int valorBascula;

    @Column(name = "total_neto", nullable = false)
    private double totalNeto;

    @Column(nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "id_owner", referencedColumnName = "id_owner", insertable = false, updatable = false)
    private OwnerEntity owner;

    @OneToOne
    @JoinColumn(name = "id_cattle", referencedColumnName = "id_cattle", insertable = false, updatable = false)
    private CattleEntity cattle;
}
