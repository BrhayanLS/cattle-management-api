package com.adgan.persitence.repository;

import com.adgan.persitence.entity.CattleEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CattleRepository extends ListCrudRepository<CattleEntity, Integer> {
  List<CattleEntity> findAllByEstadoIsTrue();
}
