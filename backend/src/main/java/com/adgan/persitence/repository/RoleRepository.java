package com.adgan.persitence.repository;

import com.adgan.persitence.entity.ERole;
import com.adgan.persitence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

/**
 * Repositorio para la gestión de roles en el sistema.
 * Proporciona métodos para realizar operaciones CRUD sobre la entidad RoleEntity.
 * Extiende JpaRepository para obtener funcionalidades básicas de persistencia.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    /**
     * Busca un rol por su ID.
     * Mantiene el tipo de retorno Optional para manejar casos donde el rol no existe.
     *
     * @param id ID del rol a buscar
     * @return Optional que contiene el rol si existe, o vacío si no existe
     */
    Optional<RoleEntity> findById(int id);
}
