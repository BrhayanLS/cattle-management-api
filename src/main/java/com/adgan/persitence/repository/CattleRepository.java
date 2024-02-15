package com.adgan.persitence.repository;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.projection.CattleResume;
import com.adgan.persitence.projection.CattleSoldResume;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CattleRepository extends ListCrudRepository<CattleEntity, Integer> {
  @Query(value =
          "SELECT c.id_cattle AS idCattle, c.nombre, CONCAT(TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()), ' años ', " +
                  "TIMESTAMPDIFF(MONTH, c.fecha_nacimiento, CURDATE()) % 12, ' meses') AS edad, " +
                  "CONCAT(o.nombre, ' ', o.apellido) AS owner " +
                  "FROM cattle c " +
                  "RIGHT JOIN owner o ON c.id_owner = o.id_owner " +
                  "WHERE c.estado = 1", nativeQuery = true)
  List<CattleResume> getCattleResume();

  @Query(value =
          "SELECT c.id_cattle AS idCattle, c.nombre, s.fecha_venta AS fechaVenta, " +
                  "CONCAT(TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, s.fecha_venta), ' años ', " +
                  "TIMESTAMPDIFF(MONTH, c.fecha_nacimiento, s.fecha_venta) % 12, ' meses') AS edad, " +
                  "CONCAT(o.nombre, ' ', o.apellido) AS owner, " +
                  "sc.peso, sc.total_neto AS totalNeto " +
                  "FROM cattle c " +
                  "RIGHT JOIN owner o ON c.id_owner = o.id_owner " +
                  "INNER JOIN sale_cattle sc ON c.id_cattle = sc.id_cattle " +
                  "INNER JOIN sale s ON sc.id_sale = s.id_sale " +
                  "WHERE c.estado = 0", nativeQuery = true)
  List<CattleSoldResume> getCattleSoldResume();

}
