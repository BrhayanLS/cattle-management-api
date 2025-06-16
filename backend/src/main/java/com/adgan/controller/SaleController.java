package com.adgan.controller;

import com.adgan.persitence.entity.SaleEntity;
import com.adgan.service.SaleCattleService;
import com.adgan.service.SaleService;
import com.adgan.service.dto.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de ventas de ganado.
 * Proporciona endpoints para realizar operaciones CRUD sobre las ventas
 * y gestionar el proceso de venta de ganado.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService, SaleCattleService saleCattleService) {
        this.saleService = saleService;
    }

    /**
     * Obtiene todas las ventas registradas en el sistema.
     * Requiere rol de ADMIN.
     *
     * @return Lista de todas las ventas
     */
    @GetMapping("/all")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SaleDTO>> getAll() { 
        return ResponseEntity.ok(this.saleService.getAll());
    }

    /**
     * Obtiene las ventas activas en el sistema.
     * Requiere rol de ADMIN o USER.
     *
     * @return Lista de ventas activas
     */
    @GetMapping("/sales")
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<SaleDTO>> getSales() {
        return ResponseEntity.ok(this.saleService.getSales());
    }

    /**
     * Obtiene una venta específica por su ID.
     * Requiere rol de ADMIN o USER.
     *
     * @param idSale ID de la venta a buscar
     * @return Venta encontrada
     */
    @GetMapping("/{idSale}")
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable int idSale) {
        return ResponseEntity.ok(this.saleService.getSaleById(idSale));
    }

    /**
     * Registra una nueva venta en el sistema.
     * Requiere rol de ADMIN.
     *
     * @param sale Datos de la nueva venta
     * @return Venta registrada
     */
    @PostMapping("/save")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleEntity> save(@RequestBody SaleDTO sale) {
        return ResponseEntity.ok(this.saleService.saveSale(sale));
    }

    /**
     * Actualiza una venta existente.
     * Requiere rol de ADMIN.
     *
     * @param sale Datos actualizados de la venta
     * @return Venta actualizada
     */
    @PutMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleEntity> update(@RequestBody SaleDTO sale){
        if (sale.getIdSale() != null && this.saleService.exists(sale.getIdSale())){
            return ResponseEntity.ok(this.saleService.saveSale(sale));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Elimina (marca como inactiva) una venta.
     * Requiere rol de ADMIN.
     *
     * @param idSale ID de la venta a eliminar
     * @return Respuesta vacía si la operación fue exitosa
     */
    @DeleteMapping("/{idSale}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable int idSale){
        if (this.saleService.exists(idSale)){
            this.saleService.delete(idSale);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
