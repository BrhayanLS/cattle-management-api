package com.adgan.web.controller;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.service.CattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cattle")
public class CattleController {

    private final CattleService cattleService;

    @Autowired
    public CattleController(CattleService cattleService) {
        this.cattleService = cattleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CattleEntity>> getAll(){
        return ResponseEntity.ok(this.cattleService.getAll());
    }

    @GetMapping
    public ResponseEntity<List<CattleEntity>> getCattles(){
        return ResponseEntity.ok(this.cattleService.getCattles());
    }

    @GetMapping("/{idCattle}")
    public ResponseEntity<CattleEntity> getCattleById(@PathVariable int idCattle) {
        return ResponseEntity.ok(this.cattleService.getById(idCattle));
    }

    @PostMapping
    public ResponseEntity<CattleEntity> addCattle(@RequestBody CattleEntity cattle){
        return ResponseEntity.ok(this.cattleService.saveCattle(cattle));
    }
    @PutMapping
    public ResponseEntity<CattleEntity> update(@RequestBody CattleEntity cattle){
        if (cattle.getIdCattle() != null && this.cattleService.exists(cattle.getIdCattle())){
            return ResponseEntity.ok(this.cattleService.saveCattle(cattle));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idCattle}")
    public ResponseEntity<Void> delete (@PathVariable int idCattle){
        if (this.cattleService.exists(idCattle)){
            this.cattleService.deleteCattle(idCattle);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
