package com.adgan.controller;

import com.adgan.persitence.entity.ERole;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/roles")
    public List<ERole> getRoles() {
        return Arrays.asList(ERole.values());
    }
}
