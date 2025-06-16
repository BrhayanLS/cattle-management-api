package com.adgan.service;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.entity.SaleCattleEntity;
import com.adgan.persitence.repository.SaleCattleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para la gestión de la relación entre ventas y ganado.
 * Proporciona métodos para verificar la existencia de registros
 * en la tabla de relación venta-ganado.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Service
public class SaleCattleService {

    private final SaleCattleRepository saleCattleRepository;

    @Autowired
    public SaleCattleService(SaleCattleRepository saleCattleRepository) {
        this.saleCattleRepository = saleCattleRepository;
    }

    /**
     * Verifica si existe un registro de venta-ganado con el ID especificado.
     *
     * @param idSale ID del registro a verificar
     * @return true si existe, false en caso contrario
     */
    public Boolean exists(int idSale) {
        return this.saleCattleRepository.existsById(idSale);
    }
}
