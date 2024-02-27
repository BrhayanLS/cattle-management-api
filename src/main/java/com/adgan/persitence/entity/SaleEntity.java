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
    @Column(name = "id_sale"/*, nullable = false*/)
    private Integer idSale;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean estado;

    @Column(name = "fecha_venta", /*nullable = false,*/ columnDefinition = "DATE")
    private LocalDate fechaVenta;

    @Column(name = "precio_kilo"/*, nullable = false*/)
    private Integer precioKilo;

    @Column(name = "valor_camion"/*, nullable = false*/)
    private Integer valorCamion;

    @Column(name = "valor_bascula"/*, nullable = false*/)
    private Integer valorBascula;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleCattleEntity> saleCattles = new ArrayList<>();

    public void addSaleCattle(SaleCattleEntity saleCattle) {
        if (saleCattle != null) {
            saleCattles.add(saleCattle);
            saleCattle.setSale(this); // Establecer la relación bidireccional
        }
    }

    @Override
    public String toString() {
        return "SaleEntity{" +
                "idSale=" + idSale +
                ", fechaVenta=" + fechaVenta +
                ", precioKilo=" + precioKilo +
                ", valorCamion=" + valorCamion +
                ", valorBascula=" + valorBascula +
                ", saleCattles=" + saleCattles +
                '}';
    }
}
