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

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CattleRepository cattleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleCattleRepository saleCattleRepository, CattleRepository cattleRepository) {
        this.saleRepository = saleRepository;
        this.cattleRepository = cattleRepository;
    }

    public List<SaleDTO> getAll() {
        List<SaleEntity> entities = this.saleRepository.findAll();
        return mapEntitiesToDTOs(entities);
    }

    public List<SaleDTO> getSales() {
        List<SaleEntity> entities = this.saleRepository.findAllByEstadoIsTrue();
        return mapEntitiesToDTOs(entities);
    }

    public SaleDTO getSaleById(int id) {
        Optional<SaleEntity> optionalEntity = this.saleRepository.findById(id);
        if (optionalEntity.isPresent()) {
            SaleEntity entity = optionalEntity.get();
            return mapEntityToDTO(entity);
        } else {
            throw new RuntimeException("No se a podido encontrar la venta indicada");
        }
    }

    @Transactional
    public SaleEntity saveSale(SaleDTO saleDTO) {
        SaleEntity sale = createSaleFromDTO(saleDTO);
        return saleRepository.save(sale);
    }

    public void delete(int idSale) {
        this.saleRepository.updateEstadoSale(idSale);
    }

    public Boolean exists(int idSale) {
        return this.saleRepository.existsById(idSale);
    }

    private List<SaleDTO> mapEntitiesToDTOs(List<SaleEntity> entities) {
        List<SaleDTO> dtos = new ArrayList<>();
        for (SaleEntity entity : entities) {
            dtos.add(mapEntityToDTO(entity));
        }
        return dtos;
    }

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