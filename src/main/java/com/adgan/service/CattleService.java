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

@Service
public class CattleService {
    private final CattleRepository cattleRepository;

    @Autowired
    public CattleService(CattleRepository cattleRepository) {
        this.cattleRepository = cattleRepository;
    }

    public CattleEntity saveCattle (CattleEntity cattle){
        cattle.setEstado(true);
        return this.cattleRepository.save(cattle);
    }

    public List<AllCattles> getAll(){
        return  this.cattleRepository.getAllCattles();
    }

    public List<AllCattles> getCattles(){
        return  this.cattleRepository.getCattles();
    }

    public List<CattleResume> getCattleResume(){
        return  this.cattleRepository.getCattleResume();
    }

    public List<CattleSoldResume> getSoldResume() {return this.cattleRepository.getCattleSoldResume();}

    public  List<AllCattles> getDeadCattles() { return this.cattleRepository.getDeadCattles();}

    public Optional<AllCattles> getCattleById(int idCattle){
        return this.cattleRepository.getCattlesById(idCattle);
    }

    public Boolean exists(int idCattle) {
        return this.cattleRepository.existsById(idCattle);
    }

    public void deleteCattle (int idCattle){
        this.cattleRepository.updateEstado(idCattle);
    }
}
