package com.adgan.persitence.repository;

import com.adgan.persitence.entity.SaleEntity;
import com.adgan.persitence.projection.AllCattles;
import com.adgan.persitence.projection.SalesCattles;
import com.adgan.service.dto.SaleDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends ListCrudRepository<SaleEntity, Integer> {

    List<SaleEntity> findAllByEstadoIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE SaleEntity se SET se.estado = false WHERE se.idSale = :idSale")
    void updateEstadoSale(@Param("idSale") int idSale);

    @Transactional
    @Modifying
    @Query("UPDATE CattleEntity c SET c.estado = false WHERE c.idCattle = :idCattle")
    void updateEstadoCattle(@Param("idCattle") int idCattle);
}
