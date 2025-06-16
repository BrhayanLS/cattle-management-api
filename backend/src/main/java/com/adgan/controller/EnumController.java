package com.adgan.controller;

import com.adgan.persitence.entity.ERole;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Controlador REST para la gestión de enumeraciones del sistema.
 * Proporciona endpoints para obtener los valores de las enumeraciones
 * utilizadas en la aplicación.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/enums")
public class EnumController {

    /**
     * Obtiene la lista de roles disponibles en el sistema.
     * Este endpoint es público y no requiere autenticación.
     *
     * @return Lista de roles disponibles (ADMIN, USER, INVITED)
     */
    @GetMapping("/roles")
    public List<ERole> getRoles() {
        return Arrays.asList(ERole.values());
    }
}
