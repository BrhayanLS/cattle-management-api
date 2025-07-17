package com.adgan.controller;

import com.adgan.persitence.entity.CattleEntity;
import com.adgan.persitence.projection.AllCattles;
import com.adgan.persitence.projection.CattleResume;
import com.adgan.persitence.projection.CattleSoldResume;
import com.adgan.service.CattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de ganado.
 * Proporciona endpoints para realizar operaciones CRUD sobre el ganado
 * y obtener diferentes vistas de los datos.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cattle")
public class CattleController {

    private final CattleService cattleService;

    @Autowired
    public CattleController(CattleService cattleService) {
        this.cattleService = cattleService;
    }

    /**
     * Obtiene todos los registros de ganado.
     * Requiere rol de ADMIN.
     *
     * @return Lista de todos los registros de ganado
     */
    @GetMapping("/all")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<AllCattles>> getAll(){
        return ResponseEntity.ok(this.cattleService.getAll());
    }

    /**
     * Obtiene los registros de ganado activos.
     * Requiere rol de ADMIN o USER.
     *
     * @return Lista de registros de ganado activos
     */
    @GetMapping("/cattles")
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<AllCattles>> getCattles(){
        return ResponseEntity.ok(this.cattleService.getCattles());
    }

    /**
     * Obtiene un registro de ganado por su ID.
     * Requiere rol de ADMIN o USER.
     *
     * @param idCattle ID del ganado a buscar
     * @return Registro de ganado encontrado
     */
    @GetMapping("/{idCattle}")
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Optional<AllCattles>> getCattleById(@PathVariable int idCattle) {
        return ResponseEntity.ok(this.cattleService.getCattleById(idCattle));
    }

    /**
     * Obtiene un resumen de los registros de ganado.
     * Accesible para todos los roles (ADMIN, USER, INVITED).
     *
     * @return Lista de resúmenes de ganado
     */
    @GetMapping("/resume")
    //@PreAuthorize("hasAnyRole('ADMIN','USER','INVITED')")
    public ResponseEntity<List<CattleResume>> getCattleResume(){
        return ResponseEntity.ok(this.cattleService.getCattleResume());
    }

    /**
     * Obtiene un resumen de los registros de ganado vendido.
     * Requiere rol de ADMIN o USER.
     *
     * @return Lista de resúmenes de ganado vendido
     */
    @GetMapping("/sold")
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<CattleSoldResume>> getSoldResume(){
        return ResponseEntity.ok(this.cattleService.getSoldResume());
    }

    /**
     * Obtiene los registros de ganado fallecido.
     * Requiere rol de ADMIN o USER.
     *
     * @return Lista de registros de ganado fallecido
     */
    @GetMapping("/dead")
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<AllCattles>> getDeadCattles(){
        return ResponseEntity.ok(this.cattleService.getDeadCattles());
    }

    /**
     * Registra un nuevo animal en el sistema.
     * Requiere rol de ADMIN o USER.
     *
     * @param cattle Datos del nuevo animal
     * @return Animal registrado
     */
    @PostMapping
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<CattleEntity> addCattle(@RequestBody CattleEntity cattle){
        return ResponseEntity.ok(this.cattleService.saveCattle(cattle));
    }

    /**
     * Actualiza los datos de un animal existente.
     * Requiere rol de ADMIN.
     *
     * @param cattle Datos actualizados del animal
     * @return Animal actualizado
     */
    @PutMapping("/update")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CattleEntity> update(@RequestBody CattleEntity cattle){
        if (cattle.getIdCattle() != null && this.cattleService.exists(cattle.getIdCattle())){
            Optional<CattleEntity> exist = this.cattleService.findById(cattle.getIdCattle());
            boolean status = exist.get().getEstado();
            return ResponseEntity.ok(this.cattleService.updateCattle(cattle, status));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Elimina (marca como inactivo) un registro de ganado.
     * Requiere rol de ADMIN.
     *
     * @param idCattle ID del animal a eliminar
     * @return Respuesta vacía si la operación fue exitosa
     */
    @DeleteMapping("/delete/{idCattle}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete (@PathVariable int idCattle){
        if (this.cattleService.exists(idCattle)){
            this.cattleService.deleteCattle(idCattle);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
