package com.adgan.controller;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.projection.AllCattles;
import com.adgan.persitence.projection.CattleResume;
import com.adgan.persitence.projection.CattleSoldResume;
import com.adgan.service.CattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cattle")
public class CattleController {

    private final CattleService cattleService;

    @Autowired
    public CattleController(CattleService cattleService) {
        this.cattleService = cattleService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<AllCattles>> getAll(){
        return ResponseEntity.ok(this.cattleService.getAll());
    }

    @GetMapping("/cattles")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<AllCattles>> getCattles(){
        return ResponseEntity.ok(this.cattleService.getCattles());
    }

    @GetMapping("/{idCattle}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Optional<AllCattles>> getCattleById(@PathVariable int idCattle) {
        return ResponseEntity.ok(this.cattleService.getCattleById(idCattle));
    }

    @GetMapping("/resume")
    @PreAuthorize("hasAnyRole('ADMIN','USER','INVITED')")
    public ResponseEntity<List<CattleResume>> getCattleResume(){
        return ResponseEntity.ok(this.cattleService.getCattleResume());
    }

    @GetMapping("/sold")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<CattleSoldResume>> getSoldResume(){
        return ResponseEntity.ok(this.cattleService.getSoldResume());
    }

    @GetMapping("/dead")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<AllCattles>> getDeadCattles(){
        return ResponseEntity.ok(this.cattleService.getDeadCattles());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<CattleEntity> addCattle(@RequestBody CattleEntity cattle){
        return ResponseEntity.ok(this.cattleService.saveCattle(cattle));
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CattleEntity> update(@RequestBody CattleEntity cattle){
        if (cattle.getIdCattle() != null && this.cattleService.exists(cattle.getIdCattle())){
            return ResponseEntity.ok(this.cattleService.saveCattle(cattle));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{idCattle}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete (@PathVariable int idCattle){
        if (this.cattleService.exists(idCattle)){
            this.cattleService.deleteCattle(idCattle);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
