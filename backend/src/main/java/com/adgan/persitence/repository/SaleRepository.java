package com.adgan.persitence.repository;

import com.adgan.persitence.entity.SaleEntity;
import com.adgan.persitence.projection.AllCattles;
import com.adgan.persitence.projection.SalesCattles;
import com.adgan.service.dto.SaleDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la gestión de ventas en el sistema.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas
 * sobre la entidad SaleEntity, incluyendo la gestión de estados de ventas y ganado.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public interface SaleRepository extends ListCrudRepository<SaleEntity, Integer> {

    /**
     * Obtiene todas las ventas que están activas en el sistema.
     *
     * @return Lista de ventas activas
     */
    List<SaleEntity> findAllByEstadoIsTrue();

    /**
     * Actualiza el estado de una venta a inactiva.
     * Esta operación es transaccional y modifica directamente la base de datos.
     *
     * @param idSale ID de la venta a desactivar
     */
    @Transactional
    @Modifying
    @Query("UPDATE SaleEntity se SET se.estado = false WHERE se.idSale = :idSale")
    void updateEstadoSale(@Param("idSale") int idSale);

    /**
     * Actualiza el estado de un animal a inactivo.
     * Esta operación es transaccional y modifica directamente la base de datos.
     * Se utiliza cuando un animal es vendido.
     *
     * @param idCattle ID del animal a marcar como vendido
     */
    @Transactional
    @Modifying
    @Query("UPDATE CattleEntity c SET c.estado = false WHERE c.idCattle = :idCattle")
    void updateEstadoCattle(@Param("idCattle") int idCattle);
}
