package com.adgan.service;

import com.adgan.persitence.entity.SaleCattleEntity;
import com.adgan.persitence.entity.SaleEntity;
import com.adgan.persitence.projection.SalesCattles;
import com.adgan.persitence.repository.SaleCattleRepository;
import com.adgan.persitence.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleCattleRepository saleCattleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleCattleRepository saleCattleRepository) {
        this.saleRepository = saleRepository;
        this.saleCattleRepository = saleCattleRepository;
    }

    public List<SalesCattles> getAll() {
        return this.saleRepository.getAllSales();
    }

    public List<SalesCattles> getSales() {
        List<SalesCattles> result = new ArrayList<>();
        List<SalesCattles> salesCattles = this.saleRepository.getAllSales();
        for (SalesCattles sale : salesCattles){
            if (sale.getEstado() == 1){
                result.add(sale);
            }
        }
        return result;
    }


    public List<SalesCattles> getSaleById(int idSale) {
        return this.saleRepository.getSalesById(idSale);
    }

    @Transactional
    public SaleEntity saveSale(SaleEntity saleEntity) {
        List<SaleCattleEntity> saleCattleList = saleEntity.getSaleCattles();
        int cant = saleCattleList.size();
        SaleEntity sale = new SaleEntity(saleEntity.getFechaVenta(), saleEntity.getPrecioKilo(), saleEntity.getValorCamion(), saleEntity.getValorBascula());
        sale.setEstado(true);
        SaleEntity saveSale = this.saleRepository.save(sale);
        
        for (SaleCattleEntity saleEntity1 : saleCattleList) {
            SaleCattleEntity detail = new SaleCattleEntity();
            detail.setCattle(saleEntity1.getCattle());
            detail.setPeso(saleEntity1.getPeso());

            double totalNeto = (saveSale.getPrecioKilo() * saleEntity1.getPeso());
            double total = (totalNeto - (((double) saveSale.getValorCamion() / cant) + ((double) saveSale.getValorBascula() / cant)));
            detail.setTotalNeto(totalNeto);
            detail.setTotal(total);

            detail.setSale(saveSale);
            this.saleCattleRepository.save(detail);
            this.saleRepository.updateEstado(saleEntity1.getCattle().getIdCattle());
        }
        return sale;
    }
/*
    @Transactional
    public SaleEntity updateSale(SaleEntity saleEntity) {
        List<SaleCattleEntity> saleCattleList = saleEntity.getSaleCattles();
        int cant = saleCattleList.size();
        SaleEntity sale = this.saleRepository.findById(saleEntity.getIdSale()).orElse(null);
        if (sale != null) {
             sale.setFechaVenta(saleEntity.getFechaVenta());
             sale.setPrecioKilo(saleEntity.getPrecioKilo());
             sale.setValorCamion(saleEntity.getValorCamion());
             sale.setValorBascula(saleEntity.getValorBascula());
             sale.setEstado(true);
             this.saleRepository.save(sale);
             for (SaleCattleEntity saleEntity1 : saleCattleList) {
                 SaleCattleEntity detail = new SaleCattleEntity();
                 detail.setCattle(saleEntity1.getCattle());
                 detail.setPeso(saleEntity1.getPeso());

                 double totalNeto = (sale.getPrecioKilo() * saleEntity1.getPeso());
                 double total = (totalNeto - (((double) sale.getValorCamion() / cant) + ((double) sale.getValorBascula() / cant)));
                 detail.setTotalNeto(totalNeto);
                 detail.setTotal(total);

                 detail.setSale(sale);
                 this.saleCattleRepository.save(detail);
             }
        }
        return sale;
    }
*/

    @Transactional
    public SaleEntity updateSale(SaleEntity saleEntity) {
        SaleEntity sale = new SaleEntity(saleEntity.getIdSale(), saleEntity.getEstado(), saleEntity.getFechaVenta(), saleEntity.getPrecioKilo(),
                saleEntity.getValorCamion(), saleEntity.getValorBascula());
        sale.setIdSale(saleEntity.getIdSale());
        sale.setEstado(true);
        sale.setFechaVenta(saleEntity.getFechaVenta());
        sale.setPrecioKilo(saleEntity.getPrecioKilo());
        sale.setValorCamion(saleEntity.getValorCamion());
        sale.setValorBascula(saleEntity.getValorBascula());
        SaleEntity saveSale = this.saleRepository.save(sale);
        return sale;
    }

    public void delete(int idSale) {
        this.saleRepository.deleteById(idSale);
    }

    public Boolean exists(int idSale) {
        return this.saleRepository.existsById(idSale);
    }
}