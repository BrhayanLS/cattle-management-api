package com.adgan.controller;

import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.service.OwnerService;
import com.adgan.service.dto.OwnerDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/all")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OwnerEntity>> getAll() {
        return ResponseEntity.ok(this.ownerService.getAll());
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OwnerEntity>> getOwners() {
        return ResponseEntity.ok(this.ownerService.getOwners());
    }

    @GetMapping("/{idOwner}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerEntity> getOwnerById(@PathVariable int idOwner) {
        return ResponseEntity.ok(this.ownerService.getById(idOwner));
    }

    @PostMapping("/save")
    public ResponseEntity<OwnerEntity> addOwner(@Valid @RequestBody OwnerDTO owner) {
        return ResponseEntity.ok(this.ownerService.saveOwner(owner));
    }

    @PutMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerEntity> update(@RequestBody OwnerDTO owner) {
        if (owner.getIdOwner() != null && this.ownerService.exists(owner.getIdOwner())) {
            return ResponseEntity.ok(this.ownerService.saveOwner(owner));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idOwner}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable int idOwner) {
        if (this.ownerService.exists(idOwner)) {
            this.ownerService.deleteOwner(idOwner);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
