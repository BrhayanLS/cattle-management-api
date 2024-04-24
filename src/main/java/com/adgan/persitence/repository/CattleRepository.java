package com.adgan.persitence.repository;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.projection.AllCattles;
import com.adgan.persitence.projection.CattleResume;
import com.adgan.persitence.projection.CattleSoldResume;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CattleRepository extends ListCrudRepository<CattleEntity, Integer> {

  @Query(value = "SELECT * FROM view_all_cattle", nativeQuery = true)
  List<AllCattles> getAllCattles();

  @Query(value = "SELECT * FROM view_cattles", nativeQuery = true)
  List<AllCattles> getCattles();

  @Query(value =
          "SELECT c.id_cattle AS idCattle, c.estado, c.nombre, c.fecha_nacimiento AS fechaNacimiento, " +
                  "o.id_owner AS idOwner, o.nombre AS nombreOwner, o.apellido, o.contacto, o.correo " +
                  "FROM cattle c " +
                  "LEFT JOIN owner o ON c.id_owner = o.id_owner " +
                  "WHERE c.id_cattle = :idCattle", nativeQuery = true)
  Optional<AllCattles> getCattlesById(@Param("idCattle") int idCattle);;


  @Query(value =
          "SELECT * FROM resume_cattles", nativeQuery = true)
  List<CattleResume> getCattleResume();

  @Query(value =
          "SELECT * FROM resume_sold", nativeQuery = true)
  List<CattleSoldResume> getCattleSoldResume();

  @Query(value =
          "SELECT * FROM dead_cattles", nativeQuery = true)
  List<AllCattles> getDeadCattles();

  @Transactional
  @Modifying
  @Query("UPDATE CattleEntity c SET c.estado = false WHERE c.idCattle = :idCattle")
  void updateEstado(@Param("idCattle") int idCattle);
}
