package com.adgan.persitence.repository;

import com.adgan.persitence.entity.SaleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends ListCrudRepository<SaleEntity, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE CattleEntity c SET c.estado = false WHERE c.idCattle = :idCattle")
    void updateEstado(@Param("idCattle") int idCattle);
/*
    @Query("SELECT COALESCE(MAX(s.idSale) + 1, 1) FROM SaleEntity s")
    int findMaxIdSalePlusOne();

    @Modifying
    @Transactional
    @Query("INSERT INTO Sale (fechaVenta, precioKilo, valorCamion, valorBascula) " +
            "VALUES (:fechaVenta, :precioKilo, :valorCamion, :valorBascula)")
    SaleEntity saveExam(@Param("fechaVenta") LocalDate fechaVenta,
                  @Param("precioKilo") int precioKilo,
                  @Param("valorCamion") int valorCamion,
                  @Param("valorBascula") int valorBascula);*/
}
