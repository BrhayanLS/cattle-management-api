package com.adgan.service;

import com.adgan.persitence.entity.SaleEntity;
import com.adgan.persitence.repository.SaleRepository;
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

    public SaleEntity saveSale (SaleEntity sale) {
        sale.setTotalNeto(sale.getPeso() * sale.getPrecioKilo());
        sale.setTotal(sale.getTotalNeto() - (sale.getValorBascula() + sale.getValorCamion()));
        return this.saleRepository.save(sale);
    }

    public void delete(int idSale){
        this.saleRepository.deleteById(idSale);
    }

    public Boolean exists(int idSale){
        return this.saleRepository.existsById(idSale);
    }
}
