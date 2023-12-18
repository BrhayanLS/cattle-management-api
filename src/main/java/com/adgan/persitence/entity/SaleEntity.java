package com.adgan.persitence.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /*@Column(name = "id_owner", nullable = false)
    private Integer idOwner;

    @Column(name = "id_cattle", nullable = false, unique = true)
    private Integer idCattle;*/

    @Column(name = "fecha_venta", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaVenta;

    /*@Column(nullable = false)
    private Double peso;*/

    @Column(name = "precio_kilo", nullable = false)
    private Integer precioKilo;

    @Column(name = "valor_camion", nullable = false)
    private Integer valorCamion;

    @Column(name = "valor_bascula", nullable = false)
    private Integer valorBascula;

    /*@Column(name = "total_neto", nullable = false)
    private Double totalNeto;

    @Column(nullable = false)
    private Double total;*/

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
    private List<SaleCattleEntity> saleCattle = new ArrayList<>();

    public void addSaleCattle(SaleCattleEntity saleCattle) {
        this.saleCattle.add(saleCattle);
        saleCattle.setSale(this);
    }

    public void removeSaleItem(SaleCattleEntity saleCattle) {
        this.saleCattle.remove(saleCattle);
        saleCattle.setSale(null);
    }

    /*@ManyToOne
    @JoinColumn(name = "id_owner", referencedColumnName = "id_owner", insertable = false, updatable = false)
    private OwnerEntity owner;

    @OneToOne
    @JoinColumn(name = "id_cattle", referencedColumnName = "id_cattle", insertable = false, updatable = false)
    private CattleEntity cattle;*/
}
