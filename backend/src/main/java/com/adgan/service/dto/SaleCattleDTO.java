package com.adgan.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para la transferencia de datos de la relación entre ventas y ganado.
 * Este objeto se utiliza para manejar los detalles específicos de cada animal en una venta,
 * incluyendo su peso y los cálculos financieros asociados.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Getter
@Setter
public class SaleCattleDTO {
    /**
     * Identificador único de la relación venta-ganado
     */
    private Integer idSaleCattle;

    /**
     * Peso del animal en la venta (en kilogramos)
     */
    private Double peso;

    /**
     * Total neto de la venta del animal (después de descuentos)
     */
    private Double totalNeto;

    /**
     * Total bruto de la venta del animal (antes de descuentos)
     */
    private Double total;

    /**
     * Identificador del animal vendido
     */
    private Integer cattleId;
}
