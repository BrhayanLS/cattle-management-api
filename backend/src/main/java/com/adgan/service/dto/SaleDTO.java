package com.adgan.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO (Data Transfer Object) para la transferencia de datos de ventas.
 * Este objeto se utiliza para validar y transferir datos entre la capa de controlador
 * y la capa de servicio en las operaciones relacionadas con ventas.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Getter
@Setter
public class SaleDTO {
    /**
     * Identificador único de la venta
     */
    private Integer idSale;

    /**
     * Estado de la venta (activa/inactiva)
     */
    private Boolean estado;

    /**
     * Fecha en que se realizó la venta
     */
    private LocalDate fechaVenta;

    /**
     * Precio por kilo del ganado en la venta
     */
    private Integer precioKilo;

    /**
     * Costo del transporte (camión) asociado a la venta
     */
    private Integer valorCamion;

    /**
     * Costo de la báscula asociado a la venta
     */
    private Integer valorBascula;

    /**
     * Lista de ganado vendido en esta venta
     */
    private List<SaleCattleDTO> saleCattles;
}

