package com.adgan.persitence.projection;

/**
 * Proyección que representa un resumen básico de la información del ganado.
 * Esta interfaz se utiliza para obtener una vista simplificada de los datos
 * del ganado, incluyendo información básica del propietario.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface CattleResume {
    /**
     * Obtiene el ID único del animal.
     * @return ID del animal
     */
    Integer getIdCattle();

    /**
     * Obtiene la edad del animal en formato legible.
     * @return Edad del animal
     */
    String getEdad();

    /**
     * Obtiene el nombre o identificación del animal.
     * @return Nombre del animal
     */
    String getNombre();

    /**
     * Obtiene el nombre completo del propietario.
     * @return Nombre del propietario
     */
    String getOwner();

    /**
     * Obtiene el estado actual del animal en formato legible.
     * @return Estado del animal
     */
    String getEstado();

    /**
     * Obtiene el número de contacto del propietario.
     * @return Número de contacto
     */
    String getContacto();
}
