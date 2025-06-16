package com.adgan.persitence.projection;

import java.time.LocalDate;

/**
 * Proyección que representa un resumen de los animales vendidos.
 * Esta interfaz se utiliza para obtener una vista simplificada de los datos
 * de los animales que han sido vendidos, incluyendo información de la venta
 * y datos básicos del propietario.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface CattleSoldResume {
    /**
     * Obtiene el ID único del animal vendido.
     * @return ID del animal
     */
    Integer getIdCattle();

    /**
     * Obtiene el nombre o identificación del animal vendido.
     * @return Nombre del animal
     */
    String getNombre();

    /**
     * Obtiene la fecha en que se realizó la venta del animal.
     * @return Fecha de venta
     */
    LocalDate getFechaVenta();

    /**
     * Obtiene la edad del animal al momento de la venta en formato legible.
     * @return Edad del animal
     */
    String getEdad();

    /**
     * Obtiene el nombre completo del propietario del animal vendido.
     * @return Nombre del propietario
     */
    String getOwner();

    /**
     * Obtiene el peso del animal al momento de la venta.
     * @return Peso en kilos
     */
    Integer getPeso();

    /**
     * Obtiene el total neto de la venta del animal.
     * Calculado como: peso * precio por kilo.
     * @return Total neto de la venta
     */
    Integer getTotalNeto();
}
