package com.adgan.persitence.repository;

import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.persitence.projection.AllCattles;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la gestión de propietarios en el sistema.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas
 * sobre la entidad OwnerEntity.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface OwnerRepository extends ListCrudRepository<OwnerEntity, Integer> {
    /**
     * Obtiene todos los propietarios que están activos en el sistema.
     *
     * @return Lista de propietarios activos
     */
    List<OwnerEntity> findAllByEstadoIsTrue();

    /**
     * Busca un propietario por su nombre de usuario.
     * Útil para la autenticación y validación de usuarios.
     *
     * @param username Nombre de usuario a buscar
     * @return Optional que contiene el propietario si existe, o vacío si no existe
     */
    Optional<OwnerEntity> findByUsername(String username);

    /**
     * Actualiza el estado de un propietario a inactivo.
     * Esta operación es transaccional y modifica directamente la base de datos.
     *
     * @param idOwner ID del propietario a desactivar
     */
    @Transactional
    @Modifying
    @Query("UPDATE OwnerEntity o SET o.estado = false WHERE o.idOwner = :idOwner")
    void updateEstado(@Param("idOwner") int idOwner);
}
