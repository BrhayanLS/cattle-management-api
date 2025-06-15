package com.adgan.controller;

import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.service.OwnerService;
import com.adgan.service.dto.OwnerDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de propietarios.
 * Proporciona endpoints para realizar operaciones CRUD sobre los propietarios
 * y gestionar el registro de usuarios del sistema.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Obtiene todos los propietarios registrados en el sistema.
     * Requiere rol de ADMIN.
     *
     * @return Lista de todos los propietarios
     */
    @GetMapping("/all")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OwnerEntity>> getAll() {
        return ResponseEntity.ok(this.ownerService.getAll());
    }

    /**
     * Obtiene los propietarios activos en el sistema.
     * Requiere rol de ADMIN.
     *
     * @return Lista de propietarios activos
     */
    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OwnerEntity>> getOwners() {
        return ResponseEntity.ok(this.ownerService.getOwners());
    }

    /**
     * Obtiene un propietario específico por su ID.
     * Requiere rol de ADMIN.
     *
     * @param idOwner ID del propietario a buscar
     * @return Propietario encontrado
     */
    @GetMapping("/{idOwner}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerEntity> getOwnerById(@PathVariable int idOwner) {
        return ResponseEntity.ok(this.ownerService.getById(idOwner));
    }

    /**
     * Registra un nuevo propietario en el sistema.
     * Este endpoint es público y no requiere autenticación.
     *
     * @param owner Datos del nuevo propietario
     * @return Propietario registrado
     */
    @PostMapping("/save")
    public ResponseEntity<OwnerEntity> addOwner(@Valid @RequestBody OwnerDTO owner) {
        return ResponseEntity.ok(this.ownerService.saveOwner(owner));
    }

    /**
     * Actualiza los datos de un propietario existente.
     * Requiere rol de ADMIN.
     *
     * @param owner Datos actualizados del propietario
     * @return Propietario actualizado
     */
    @PutMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerEntity> update(@RequestBody OwnerDTO owner) {
        if (owner.getIdOwner() != null && this.ownerService.exists(owner.getIdOwner())) {
            return ResponseEntity.ok(this.ownerService.saveOwner(owner));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Elimina (marca como inactivo) un propietario.
     * Requiere rol de ADMIN.
     *
     * @param idOwner ID del propietario a eliminar
     * @return Respuesta vacía si la operación fue exitosa
     */
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
