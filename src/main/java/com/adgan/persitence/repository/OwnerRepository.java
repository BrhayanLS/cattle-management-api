package com.adgan.persitence.repository;

import com.adgan.persitence.entity.OwnerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends ListCrudRepository<OwnerEntity, Integer> {
    List<OwnerEntity> findAllByEstadoIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE OwnerEntity o SET o.estado = false WHERE o.idOwner = :idOwner")
    void updateEstado(@Param("idOwner") int idOwner);
}
