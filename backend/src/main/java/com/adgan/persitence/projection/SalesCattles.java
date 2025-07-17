package com.adgan.persitence.projection;

import java.time.LocalDate;

/**
 * Proyección que representa una vista detallada de las ventas de ganado.
 * Esta interfaz se utiliza para obtener información consolidada de las ventas,
 * incluyendo detalles del animal vendido y cálculos financieros.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface SalesCattles {
    /**
     * Obtiene el ID único de la venta.
     * @return ID de la venta
     */
    Integer getIdSale();

    /**
     * Obtiene el estado actual de la venta (activa/inactiva).
     * @return Estado de la venta
     */
    Byte getEstado();

    /**
     * Obtiene la fecha en que se realizó la venta.
     * @return Fecha de venta
     */
    LocalDate getFechaVenta();

    /**
     * Obtiene el precio por kilo establecido en la venta.
     * @return Precio por kilo
     */
    Integer getPrecioKilo();

    /**
     * Obtiene el costo de la báscula en la venta.
     * @return Valor de la báscula
     */
    Integer getValorBascula();

    /**
     * Obtiene el costo del transporte en la venta.
     * @return Valor del transporte
     */
    Integer getValorCamion();

    /**
     * Obtiene el peso total del animal vendido.
     * @return Peso en kilos
     */
    Integer peso();

    /**
     * Obtiene el total final de la venta.
     * Incluye todos los costos adicionales.
     * @return Total de la venta
     */
    Integer getTotal();

    /**
     * Obtiene el total neto de la venta.
     * Calculado como: peso * precio por kilo.
     * @return Total neto
     */
    Integer getTotalNeto();

    /**
     * Obtiene el nombre o identificación del animal vendido.
     * @return Nombre del animal
     */
    String getNombre();
}
