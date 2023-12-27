package com.adgan.service;

import com.adgan.persitence.entity.SaleCattleEntity;
import com.adgan.persitence.entity.SaleEntity;
import com.adgan.persitence.repository.SaleCattleRepository;
import com.adgan.persitence.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleCattleRepository saleCattleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleCattleRepository saleCattleRepository) {
        this.saleRepository = saleRepository;
        this.saleCattleRepository = saleCattleRepository;
    }

    public List<SaleEntity> getAll() {
        return this.saleRepository.findAll();
    }

    public SaleEntity getById(int idSale) {
        return this.saleRepository.findById(idSale).orElse(null);
    }

    @Transactional
    public SaleEntity saveSale(SaleEntity saleEntity) {
        List<SaleCattleEntity> saleCattlList = saleEntity.getSaleCattles();
        int cant = saleCattlList.size();
        SaleEntity sale = new SaleEntity(saleEntity.getFechaVenta(), saleEntity.getPrecioKilo(), saleEntity.getValorCamion(), saleEntity.getValorBascula());
        SaleEntity saveSale = this.saleRepository.save(sale);
        
        for (SaleCattleEntity saleEntity1 : saleCattlList) {
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
    private void saveTotalesCattle(SaleEntity sale) {
        List<SaleCattleEntity> saleCattleList = sale.getSaleCattles();
        int cant = saleCattleList.size();

        for (SaleCattleEntity saleCattle : saleCattleList) {
            double totalNeto = sale.getPrecioKilo() * saleCattle.getPeso();
            double total = totalNeto -
                    (((double) sale.getValorBascula() / cant) + ((double) sale.getValorCamion() / cant));

            saleCattle.setTotalNeto(totalNeto);
            saleCattle.setTotal(total);
        }
    }

    @Transactional
    public SaleEntity saveSale (SaleEntity sale) {
        this.saleRepository.saveExam(sale.getFechaVenta(), sale.getPrecioKilo(), sale.getValorCamion(), sale.getValorBascula());
        saveTotalesCattle(sale);
        updateEstadoCattle(sale);
        return this.saleRepository.save(sale);
    }

    @Transactional
    public SaleEntity saveSale(SaleEntity sale) {
        SaleEntity savedSale = this.saleRepository.save(sale); // Primero guarda la venta

        saveTotalesCattle(savedSale); // Luego realiza las operaciones adicionales
        updateEstadoCattle(savedSale);

        return savedSale;
    }

    private void saveTotalesCattle(SaleEntity sale) {
        List<SaleCattleEntity> saleCattleList = sale.getSaleCattle();
        int cant = saleCattleList.size();

        for (SaleCattleEntity saleCattle : saleCattleList){
            double totalNeto = sale.getPrecioKilo() * saleCattle.getPeso();
            double total = totalNeto -
                    (((double) sale.getValorBascula() /cant) + ((double) sale.getValorCamion() /cant));

            saleCattle.setTotalNeto(totalNeto);
            saleCattle.setTotal(total);
            saleCattle.setIdSale(sale.getIdSale());

            this.saleCattleRepository.save(saleCattle);
        }
    }

    private void updateEstadoCattle(SaleEntity sale) {
        List<SaleCattleEntity> saleCattleList = sale.getSaleCattle();
        System.out.println("-------------------------------------------------------------");
        System.out.println(saleCattleList);

        for (SaleCattleEntity saleCattle : saleCattleList) {
            int idCattle = saleCattle.getIdCattle();
            this.saleRepository.updateEstado(idCattle);
        }
    }*/

    public void delete(int idSale) {
        this.saleRepository.deleteById(idSale);
    }

    public Boolean exists(int idSale) {
        return this.saleRepository.existsById(idSale);
    }
}