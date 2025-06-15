package com.adgan.persitence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa la relación entre una venta y un animal.
 * Esta clase mapea la tabla 'sale_cattle' en la base de datos y contiene
 * la información específica de cada animal en una venta, incluyendo
 * su peso y los cálculos financieros asociados.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "sale_cattle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleCattleEntity {

    /**
     * Identificador único de la relación venta-animal.
     * Se genera automáticamente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale_cattle", nullable = false)
    private Integer idSaleCattle;

    /**
     * Peso del animal en la venta.
     */
    private Double peso;

    /**
     * Total neto de la venta del animal.
     * Calculado como: peso * precio por kilo
     */
    private Double totalNeto;

    /**
     * Total final de la venta del animal.
     * Incluye costos adicionales como transporte y báscula.
     */
    private Double total;

    /**
     * Venta a la que pertenece este registro.
     * Relación muchos a uno con SaleEntity.
     */
    @ManyToOne
    @JoinColumn(name = "id_sale", referencedColumnName = "id_sale")
    @JsonIgnore
    private SaleEntity sale;

    /**
     * Animal vendido en este registro.
     * Relación uno a uno con CattleEntity.
     */
    @OneToOne
    @JoinColumn(name = "id_cattle")
    @JsonIgnore
    private CattleEntity cattle;

    /**
     * Representación en cadena de la relación venta-animal.
     * Incluye ID, venta, animal, peso y totales.
     *
     * @return Cadena con la información de la relación
     */
    @Override
    public String toString() {
        return "SaleCattleEntity{" +
                "idSaleCattle=" + idSaleCattle +
                ", sale=" + sale +
                ", cattle=" + cattle +
                ", peso=" + peso +
                ", totalNeto=" + totalNeto +
                ", total=" + total +
                '}';
    }
}
