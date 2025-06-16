package com.adgan.persitence.repository;

import com.adgan.persitence.entity.SaleCattleEntity;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Repositorio para la gestión de la relación entre ventas y ganado.
 * Proporciona métodos para realizar operaciones CRUD sobre la entidad SaleCattleEntity.
 * Extiende ListCrudRepository para obtener funcionalidades básicas de persistencia.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface SaleCattleRepository extends ListCrudRepository<SaleCattleEntity, Integer> {
}
