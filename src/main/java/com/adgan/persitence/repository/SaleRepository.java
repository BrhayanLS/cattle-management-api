package com.adgan.persitence.repository;

import com.adgan.persitence.entity.SaleEntity;
import com.adgan.persitence.projection.AllCattles;
import com.adgan.persitence.projection.SalesCattles;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends ListCrudRepository<SaleEntity, Integer> {

    @Query(value = "SELECT * FROM sales_cattles", nativeQuery = true)
    List<SalesCattles> getAllSales();

    @Query(value =
            "SELECT s.id_sale as idSale, s.estado, s.fecha_venta as fechaVenta, s.precio_kilo as precioKilo, " +
                    "s.valor_bascula as valorBascula, s.valor_camion as valorCamion, sc.peso, sc.total, " +
                    "sc.total_neto as totalNeto, c.nombre " +
                    "FROM sale s " +
                    "LEFT JOIN sale_cattle sc ON s.id_sale = sc.id_sale " +
                    "LEFT JOIN cattle c ON sc.id_cattle = c.id_cattle " +
                    "WHERE s.id_sale = :idSale", nativeQuery = true)
    List<SalesCattles> getSalesById(@Param("idSale") int idSale);;

    @Transactional
    @Modifying
    @Query("UPDATE SaleEntity se SET se.estado = false WHERE se.idSale = :idSale")
    void updateEstadoSale(@Param("idSale") int idSale);

    @Transactional
    @Modifying
    @Query("UPDATE CattleEntity c SET c.estado = false WHERE c.idCattle = :idCattle")
    void updateEstado(@Param("idCattle") int idCattle);
}
