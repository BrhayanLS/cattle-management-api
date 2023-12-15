package com.adgan.persitence.repository;

import com.adgan.persitence.entity.SaleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends ListCrudRepository<SaleEntity, Integer> {

    @Modifying
    @Query("UPDATE CattleEntity c SET c.estado = false WHERE c.idCattle = :idCattle")
    void updateEstado(@Param("idCattle") int idCattle);


}
