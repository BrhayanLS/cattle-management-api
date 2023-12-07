package com.adgan.web.controller;

import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

@Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<OwnerEntity>> getAll() {
    return ResponseEntity.ok(this.ownerService.getAll());
    }
}
