package com.adgan.web.controller;

import com.adgan.persitence.entity.SaleEntity;
import com.adgan.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleEntity>> getAll () {
        return ResponseEntity.ok(this.saleService.getAll());
    }

    @GetMapping("/{idSale}")
    public ResponseEntity<SaleEntity> getById (@PathVariable int idSale){
        return ResponseEntity.ok(this.saleService.getById(idSale));
    }

    @PostMapping
    public ResponseEntity<SaleEntity> save(@RequestBody SaleEntity sale) {
        return ResponseEntity.ok(this.saleService.saveSale(sale));
    }

    @PutMapping
    public ResponseEntity<SaleEntity> update(@RequestBody SaleEntity sale){
        if (sale.getIdSale() != null && this.saleService.exists(sale.getIdSale())){
            return ResponseEntity.ok(this.saleService.saveSale(sale));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idSale}")
    public ResponseEntity<Void> delete(@PathVariable int idSale){
        if (this.saleService.exists(idSale)){
            this.saleService.delete(idSale);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
