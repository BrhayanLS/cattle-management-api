package com.adgan.persitence.repository;

import com.adgan.persitence.entity.OwnerEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OwnerRepository extends ListCrudRepository<OwnerEntity, Integer> {
    List<OwnerEntity> findAllByEstadoIsTrue();
}
