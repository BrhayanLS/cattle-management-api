package com.adgan.controller;

import com.adgan.persitence.entity.SaleEntity;
import com.adgan.service.SaleCattleService;
import com.adgan.service.SaleService;
import com.adgan.service.dto.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService, SaleCattleService saleCattleService) {
        this.saleService = saleService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SaleDTO>> getAll() { return ResponseEntity.ok(this.saleService.getAll());}

    @GetMapping("/sales")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<SaleDTO>> getSales() {
        return ResponseEntity.ok(this.saleService.getSales());
    }

    @GetMapping("/sale/{idSale}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable int idSale) {
        return ResponseEntity.ok(this.saleService.getSaleById(idSale));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleEntity> save(@RequestBody SaleDTO sale) {
        return ResponseEntity.ok(this.saleService.saveSale(sale));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleEntity> update(@RequestBody SaleDTO sale){
        if (sale.getIdSale() != null && this.saleService.exists(sale.getIdSale())){
            return ResponseEntity.ok(this.saleService.saveSale(sale));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idSale}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable int idSale){
        if (this.saleService.exists(idSale)){
            this.saleService.delete(idSale);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
