package com.adgan.service;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.projection.AllCattles;
import com.adgan.persitence.projection.CattleResume;
import com.adgan.persitence.projection.CattleSoldResume;
import com.adgan.persitence.repository.CattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de ganado.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas
 * sobre el ganado en el sistema.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Service
public class CattleService {
    private final CattleRepository cattleRepository;

    @Autowired
    public CattleService(CattleRepository cattleRepository) {
        this.cattleRepository = cattleRepository;
    }

    /**
     * Guarda un nuevo registro de ganado en el sistema.
     * El estado se establece como activo por defecto.
     *
     * @param cattle Entidad de ganado a guardar
     * @return Ganado guardado
     */
    public CattleEntity saveCattle (CattleEntity cattle){
        cattle.setEstado(true);
        return this.cattleRepository.save(cattle);
    }

    /**
     * Actualiza un registro de ganado existente.
     *
     * @param cattle Entidad de ganado con datos actualizados
     * @param status Estado a establecer
     * @return Ganado actualizado
     */
    public CattleEntity updateCattle (CattleEntity cattle, boolean status){
        cattle.setEstado(status);
        return this.cattleRepository.save(cattle);
    }

    /**
     * Busca un registro de ganado por su ID.
     *
     * @param id ID del ganado a buscar
     * @return Ganado encontrado (si existe)
     */
    public Optional<CattleEntity> findById (int id) {
        return this.cattleRepository.findById(id);
    }

    /**
     * Obtiene todos los registros de ganado.
     *
     * @return Lista de todos los registros de ganado
     */
    public List<AllCattles> getAll(){
        return  this.cattleRepository.getAllCattles();
    }

    /**
     * Obtiene los registros de ganado activos.
     *
     * @return Lista de registros de ganado activos
     */
    public List<AllCattles> getCattles(){
        return  this.cattleRepository.getCattles();
    }

    /**
     * Obtiene un resumen de los registros de ganado.
     *
     * @return Lista de resúmenes de ganado
     */
    public List<CattleResume> getCattleResume(){
        return  this.cattleRepository.getCattleResume();
    }

    /**
     * Obtiene un resumen de los registros de ganado vendido.
     *
     * @return Lista de resúmenes de ganado vendido
     */
    public List<CattleSoldResume> getSoldResume() {
        return this.cattleRepository.getCattleSoldResume();
    }

    /**
     * Obtiene los registros de ganado fallecido.
     *
     * @return Lista de registros de ganado fallecido
     */
    public List<AllCattles> getDeadCattles() { 
        return this.cattleRepository.getDeadCattles();
    }

    /**
     * Obtiene un registro de ganado por su ID con información detallada.
     *
     * @param idCattle ID del ganado a buscar
     * @return Registro de ganado encontrado (si existe)
     */
    public Optional<AllCattles> getCattleById(int idCattle){
        return this.cattleRepository.getCattlesById(idCattle);
    }

    /**
     * Verifica si existe un registro de ganado con el ID especificado.
     *
     * @param idCattle ID del ganado a verificar
     * @return true si existe, false en caso contrario
     */
    public Boolean exists(int idCattle) {
        return this.cattleRepository.existsById(idCattle);
    }

    /**
     * Marca como inactivo un registro de ganado.
     *
     * @param idCattle ID del ganado a marcar como inactivo
     */
    public void deleteCattle (int idCattle){
        this.cattleRepository.updateEstado(idCattle);
    }
}
