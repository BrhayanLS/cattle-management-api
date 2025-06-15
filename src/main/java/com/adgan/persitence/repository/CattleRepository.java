package com.adgan.persitence.repository;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.projection.AllCattles;
import com.adgan.persitence.projection.CattleResume;
import com.adgan.persitence.projection.CattleSoldResume;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la gestión de ganado en el sistema.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas
 * sobre la entidad CattleEntity, incluyendo vistas y resúmenes específicos.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface CattleRepository extends ListCrudRepository<CattleEntity, Integer> {

    /**
     * Obtiene todos los registros de ganado con información detallada.
     * Utiliza la vista 'view_all_cattle' para obtener datos completos.
     *
     * @return Lista de todos los registros de ganado
     */
    @Query(value = "SELECT * FROM view_all_cattle", nativeQuery = true)
    List<AllCattles> getAllCattles();

    /**
     * Obtiene los registros de ganado activos con información detallada.
     * Utiliza la vista 'view_cattles' para obtener datos filtrados.
     *
     * @return Lista de registros de ganado activos
     */
    @Query(value = "SELECT * FROM view_cattles", nativeQuery = true)
    List<AllCattles> getCattles();

    /**
     * Busca un registro de ganado específico por su ID.
     * Incluye información detallada del propietario.
     *
     * @param idCattle ID del animal a buscar
     * @return Optional que contiene el registro si existe, o vacío si no existe
     */
    @Query(value =
            "SELECT c.id_cattle AS idCattle, c.estado, c.nombre, c.fecha_nacimiento AS fechaNacimiento, " +
                    "o.id_owner AS idOwner, o.nombre AS nombreOwner, o.apellido, o.contacto, o.correo " +
                    "FROM cattle c " +
                    "LEFT JOIN owner o ON c.id_owner = o.id_owner " +
                    "WHERE c.id_cattle = :idCattle", nativeQuery = true)
    Optional<AllCattles> getCattlesById(@Param("idCattle") int idCattle);

    /**
     * Obtiene un resumen básico de todos los registros de ganado.
     * Utiliza la vista 'resume_cattles' para obtener datos simplificados.
     *
     * @return Lista de resúmenes de ganado
     */
    @Query(value = "SELECT * FROM resume_cattles", nativeQuery = true)
    List<CattleResume> getCattleResume();

    /**
     * Obtiene un resumen de los animales vendidos.
     * Utiliza la vista 'resume_sold' para obtener datos de ventas.
     *
     * @return Lista de resúmenes de animales vendidos
     */
    @Query(value = "SELECT * FROM resume_sold", nativeQuery = true)
    List<CattleSoldResume> getCattleSoldResume();

    /**
     * Obtiene los registros de ganado inactivo (muerto o eliminado).
     * Utiliza la vista 'dead_cattles' para obtener datos filtrados.
     *
     * @return Lista de registros de ganado inactivo
     */
    @Query(value = "SELECT * FROM dead_cattles", nativeQuery = true)
    List<AllCattles> getDeadCattles();

    /**
     * Actualiza el estado de un registro de ganado a inactivo.
     * Esta operación es transaccional y modifica directamente la base de datos.
     *
     * @param idCattle ID del animal a desactivar
     */
    @Transactional
    @Modifying
    @Query("UPDATE CattleEntity c SET c.estado = false WHERE c.idCattle = :idCattle")
    void updateEstado(@Param("idCattle") int idCattle);
}
