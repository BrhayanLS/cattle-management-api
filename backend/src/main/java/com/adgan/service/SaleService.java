package com.adgan.service;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.entity.SaleCattleEntity;
import com.adgan.persitence.entity.SaleEntity;
import com.adgan.persitence.repository.CattleRepository;
import com.adgan.persitence.repository.SaleCattleRepository;
import com.adgan.persitence.repository.SaleRepository;
import com.adgan.service.dto.SaleCattleDTO;
import com.adgan.service.dto.SaleDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de ventas de ganado.
 * Proporciona métodos para realizar operaciones CRUD sobre las ventas
 * y gestionar el proceso de venta de ganado, incluyendo cálculos de precios
 * y actualización de estados.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CattleRepository cattleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleCattleRepository saleCattleRepository, CattleRepository cattleRepository) {
        this.saleRepository = saleRepository;
        this.cattleRepository = cattleRepository;
    }

    /**
     * Obtiene todas las ventas registradas en el sistema.
     *
     * @return Lista de todas las ventas
     */
    public List<SaleDTO> getAll() {
        List<SaleEntity> entities = this.saleRepository.findAll();
        return mapEntitiesToDTOs(entities);
    }

    /**
     * Obtiene las ventas activas en el sistema.
     *
     * @return Lista de ventas activas
     */
    public List<SaleDTO> getSales() {
        List<SaleEntity> entities = this.saleRepository.findAllByEstadoIsTrue();
        return mapEntitiesToDTOs(entities);
    }

    /**
     * Obtiene una venta específica por su ID.
     *
     * @param id ID de la venta a buscar
     * @return Venta encontrada
     * @throws RuntimeException si no se encuentra la venta
     */
    public SaleDTO getSaleById(int id) {
        Optional<SaleEntity> optionalEntity = this.saleRepository.findById(id);
        if (optionalEntity.isPresent()) {
            SaleEntity entity = optionalEntity.get();
            return mapEntityToDTO(entity);
        } else {
            throw new RuntimeException("No se a podido encontrar la venta indicada");
        }
    }

    /**
     * Guarda una nueva venta en el sistema.
     * Este método es transaccional y actualiza el estado del ganado vendido.
     *
     * @param saleDTO Datos de la venta a guardar
     * @return Venta guardada
     */
    @Transactional
    public SaleEntity saveSale(SaleDTO saleDTO) {
        SaleEntity sale = createSaleFromDTO(saleDTO);
        return saleRepository.save(sale);
    }

    /**
     * Marca como inactiva una venta.
     *
     * @param idSale ID de la venta a marcar como inactiva
     */
    public void delete(int idSale) {
        this.saleRepository.updateEstadoSale(idSale);
    }

    /**
     * Verifica si existe una venta con el ID especificado.
     *
     * @param idSale ID de la venta a verificar
     * @return true si existe, false en caso contrario
     */
    public Boolean exists(int idSale) {
        return this.saleRepository.existsById(idSale);
    }

    /**
     * Convierte una lista de entidades de venta a DTOs.
     *
     * @param entities Lista de entidades de venta
     * @return Lista de DTOs de venta
     */
    private List<SaleDTO> mapEntitiesToDTOs(List<SaleEntity> entities) {
        List<SaleDTO> dtos = new ArrayList<>();
        for (SaleEntity entity : entities) {
            dtos.add(mapEntityToDTO(entity));
        }
        return dtos;
    }

    /**
     * Convierte una entidad de venta a DTO.
     *
     * @param entity Entidad de venta
     * @return DTO de venta
     */
    private SaleDTO mapEntityToDTO(SaleEntity entity) {
        SaleDTO dto = new SaleDTO();
        dto.setIdSale(entity.getIdSale());
        dto.setEstado(entity.getEstado());
        dto.setFechaVenta(entity.getFechaVenta());
        dto.setPrecioKilo(entity.getPrecioKilo());
        dto.setValorCamion(entity.getValorCamion());
        dto.setValorBascula(entity.getValorBascula());
        dto.setSaleCattles(mapSaleCattlesToDTOs(entity.getSaleCattles()));
        return dto;
    }

    /**
     * Convierte una lista de entidades de venta-ganado a DTOs.
     *
     * @param saleCattleEntities Lista de entidades de venta-ganado
     * @return Lista de DTOs de venta-ganado
     */
    private List<SaleCattleDTO> mapSaleCattlesToDTOs(List<SaleCattleEntity> saleCattleEntities) {
        List<SaleCattleDTO> dtos = new ArrayList<>();
        for (SaleCattleEntity saleCattleEntity : saleCattleEntities) {
            SaleCattleDTO saleCattleDTO = new SaleCattleDTO();
            saleCattleDTO.setIdSaleCattle(saleCattleEntity.getIdSaleCattle());
            saleCattleDTO.setPeso(saleCattleEntity.getPeso());
            saleCattleDTO.setTotalNeto(saleCattleEntity.getTotalNeto());
            saleCattleDTO.setTotal(saleCattleEntity.getTotal());
            saleCattleDTO.setCattleId(saleCattleEntity.getCattle().getIdCattle());
            dtos.add(saleCattleDTO);
        }
        return dtos;
    }

    /**
     * Crea una entidad de venta a partir de un DTO.
     * Incluye la creación de las relaciones con el ganado vendido.
     *
     * @param saleDTO DTO de venta
     * @return Entidad de venta creada
     */
    private SaleEntity createSaleFromDTO(SaleDTO saleDTO) {
        SaleEntity sale = new SaleEntity();
        int cant = 0;
        List<SaleCattleDTO> saleCattles = saleDTO.getSaleCattles();
        if (saleCattles != null) {
            cant = saleCattles.size();
        }
        sale.setFechaVenta(saleDTO.getFechaVenta());
        sale.setPrecioKilo(saleDTO.getPrecioKilo());
        sale.setValorCamion(saleDTO.getValorCamion());
        sale.setValorBascula(saleDTO.getValorBascula());
        sale.setEstado(true);

        if (saleDTO.getIdSale() != null) {
            sale.setIdSale(saleDTO.getIdSale());
        }

        for (SaleCattleDTO saleCattleDTO : saleDTO.getSaleCattles()) {
            SaleCattleEntity saleCattle = createSaleCattleFromDTO(saleCattleDTO, saleDTO, cant);
            sale.addSaleCattle(saleCattle);
        }
        return sale;
    }

    /**
     * Crea una entidad de venta-ganado a partir de un DTO.
     * Realiza los cálculos de totales y actualiza el estado del ganado.
     *
     * @param saleCattleDTO DTO de venta-ganado
     * @param saleDTO DTO de venta
     * @param cant Cantidad de animales en la venta
     * @return Entidad de venta-ganado creada
     * @throws RuntimeException si no se encuentra el ganado
     */
    private SaleCattleEntity createSaleCattleFromDTO(SaleCattleDTO saleCattleDTO, SaleDTO saleDTO, Integer cant) {
        SaleCattleEntity saleCattle = new SaleCattleEntity();
        saleCattle.setPeso(saleCattleDTO.getPeso());

        double totalNeto = (saleDTO.getPrecioKilo() * saleCattleDTO.getPeso());
        double total = (totalNeto - ((saleDTO.getValorCamion() / cant) + (saleDTO.getValorBascula()/cant)));
        saleCattle.setTotalNeto(totalNeto);
        saleCattle.setTotal(total);
        if (saleCattleDTO.getIdSaleCattle() != null) {
            saleCattle.setIdSaleCattle(saleCattleDTO.getIdSaleCattle());
        }
        Optional<CattleEntity> optionalCattle = cattleRepository.findById(saleCattleDTO.getCattleId());
        if (optionalCattle.isPresent()){
            saleCattle.setCattle(optionalCattle.get());
            this.saleRepository.updateEstadoCattle(saleCattleDTO.getCattleId());
        } else {
            throw new RuntimeException("No se puede encontrar el id suministrado");
        }
        return saleCattle;
    }
}