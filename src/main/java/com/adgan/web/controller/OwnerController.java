package com.adgan.web.controller;

import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.service.OwnerService;
import com.adgan.service.dto.OwnerDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OwnerEntity>> getAll() {
        return ResponseEntity.ok(this.ownerService.getAll());
    }

    @GetMapping
    public ResponseEntity<List<OwnerEntity>> getOwners() {
        return ResponseEntity.ok(this.ownerService.getOwners());
    }

    @GetMapping("/{idOwner}")
    public ResponseEntity<OwnerEntity> getOwnerById(@PathVariable int idOwner) {
        return ResponseEntity.ok(this.ownerService.getById(idOwner));
    }

    @PostMapping
    public ResponseEntity<OwnerEntity> addOwner(@Valid @RequestBody OwnerDTO owner) {
        return ResponseEntity.ok(this.ownerService.saveOwner(owner));
    }

    @PutMapping
    public ResponseEntity<OwnerEntity> update(@RequestBody OwnerDTO owner) {
        if (owner.getIdOwner() != null && this.ownerService.exists(owner.getIdOwner())) {
            return ResponseEntity.ok(this.ownerService.saveOwner(owner));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idOwner}")
    public ResponseEntity<Void> delete(@PathVariable int idOwner) {
        if (this.ownerService.exists(idOwner)) {
            this.ownerService.deleteOwner(idOwner);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
