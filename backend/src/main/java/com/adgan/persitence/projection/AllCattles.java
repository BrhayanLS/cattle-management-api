package com.adgan.persitence.projection;

import java.time.LocalDate;

/**
 * Proyección que representa una vista completa de los datos del ganado.
 * Esta interfaz se utiliza para obtener una vista optimizada de los datos
 * del ganado, incluyendo información del propietario.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface AllCattles {
    /**
     * Obtiene el ID único del animal
     * @return ID del animal
     */
    Integer getIdCattle();

    /**
     * Obtiene el estado actual del animal (activo/inactivo)
     * @return Estado del animal
     */
    Byte getEstado();

    /**
     * Obtiene el nombre del animal
     * @return Nombre del animal
     */
    String getNombre();

    /**
     * Obtiene la fecha de nacimiento del animal
     * @return Fecha de nacimiento
     */
    LocalDate getFechaNacimiento();

    /**
     * Obtiene el ID del propietario del animal
     * @return ID del propietario
     */
    Integer getIdOwner();

    /**
     * Obtiene el nombre del propietario
     * @return Nombre del propietario
     */
    String getNombreOwner();

    /**
     * Obtiene el apellido del propietario
     * @return Apellido del propietario
     */
    String getApellido();

    /**
     * Obtiene el número de contacto del propietario
     * @return Número de contacto
     */
    String getContacto();

    /**
     * Obtiene el correo electrónico del propietario
     * @return Correo electrónico
     */
    String getCorreo();
}
