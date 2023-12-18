package com.adgan.service;

import com.adgan.persitence.entity.SaleCattleEntity;
import com.adgan.persitence.entity.SaleEntity;
import com.adgan.persitence.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<SaleEntity> getAll() {
        return this.saleRepository.findAll();
    }

    public SaleEntity getById(int idSale) {
        return this.saleRepository.findById(idSale).orElse(null);
    }

    /*public SaleEntity saveSale (SaleEntity sale) {
        sale.setTotalNeto(sale.getPeso() * sale.getPrecioKilo());
        sale.setTotal(sale.getTotalNeto() - (sale.getValorBascula() + sale.getValorCamion()));
        this.saleRepository.updateEstado(sale.getIdCattle());
        return this.saleRepository.save(sale);
    }*/

    @Transactional
    public SaleEntity saveSale (SaleEntity sale) {
        saveTotalesCattle(sale);
        updateEstadoCattle(sale);
        return this.saleRepository.save(sale);
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
        }
    }

    private void updateEstadoCattle(SaleEntity sale) {
        List<SaleCattleEntity> saleCattleList = sale.getSaleCattle();
        System.out.println("-------------------------------------------------------------");
        System.out.println(saleCattleList);

        for (SaleCattleEntity saleCattle : saleCattleList) {
            int idCattle = saleCattle.getCattle().getIdCattle();
            this.saleRepository.updateEstado(idCattle);
        }
    }

    public void delete(int idSale){
        this.saleRepository.deleteById(idSale);
    }

    public Boolean exists(int idSale){
        return this.saleRepository.existsById(idSale);
    }
}
