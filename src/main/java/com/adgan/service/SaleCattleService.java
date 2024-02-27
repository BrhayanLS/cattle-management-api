package com.adgan.service;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.entity.SaleCattleEntity;
import com.adgan.persitence.repository.SaleCattleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleCattleService {

    private final SaleCattleRepository saleCattleRepository;

    @Autowired
    public SaleCattleService(SaleCattleRepository saleCattleRepository) {
        this.saleCattleRepository = saleCattleRepository;
    }

    public Boolean exists(int idSale) {
        return this.saleCattleRepository.existsById(idSale);
    }
}
