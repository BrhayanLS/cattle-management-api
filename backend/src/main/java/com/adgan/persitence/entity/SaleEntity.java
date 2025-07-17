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

/**
 * Entidad que representa una venta en el sistema.
 * Esta clase mapea la tabla 'sale' en la base de datos y contiene
 * la información de cada venta, incluyendo fechas, precios y
 * la lista de animales vendidos.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {

    /**
     * Identificador único de la venta.
     * Se genera automáticamente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale"/*, nullable = false*/)
    private Integer idSale;

    /**
     * Estado de la venta en el sistema.
     * true = activa, false = inactiva
     */
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean estado;

    /**
     * Fecha en que se realizó la venta.
     */
    @Column(name = "fecha_venta", /*nullable = false,*/ columnDefinition = "DATE")
    private LocalDate fechaVenta;

    /**
     * Precio por kilo del ganado en la venta.
     */
    @Column(name = "precio_kilo"/*, nullable = false*/)
    private Integer precioKilo;

    /**
     * Costo del transporte para la venta.
     */
    @Column(name = "valor_camion"/*, nullable = false*/)
    private Integer valorCamion;

    /**
     * Costo de la báscula para la venta.
     */
    @Column(name = "valor_bascula"/*, nullable = false*/)
    private Integer valorBascula;

    /**
     * Lista de animales vendidos en esta venta.
     * Relación uno a muchos con SaleCattleEntity.
     */
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleCattleEntity> saleCattles = new ArrayList<>();

    /**
     * Agrega un animal a la lista de ventas.
     * Establece la relación bidireccional entre la venta y el animal.
     *
     * @param saleCattle Animal a agregar a la venta
     */
    public void addSaleCattle(SaleCattleEntity saleCattle) {
        if (saleCattle != null) {
            saleCattles.add(saleCattle);
            saleCattle.setSale(this); // Establecer la relación bidireccional
        }
    }

    /**
     * Representación en cadena de la venta.
     * Incluye ID, fecha, precios y lista de animales.
     *
     * @return Cadena con la información de la venta
     */
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
