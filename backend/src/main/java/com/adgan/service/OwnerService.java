package com.adgan.service;

import com.adgan.persitence.entity.ERole;
import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.persitence.entity.RoleEntity;
import com.adgan.persitence.repository.OwnerRepository;
import com.adgan.persitence.repository.RoleRepository;
import com.adgan.service.dto.OwnerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de propietarios del sistema.
 * Proporciona operaciones CRUD para propietarios y manejo de roles.
 * Incluye funcionalidades para crear, actualizar, eliminar y consultar propietarios,
 * así como la gestión de sus estados y roles asociados.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@Service
public class OwnerService {
    @Autowired
    private final OwnerRepository ownerRepository;
    @Autowired
    private final RoleRepository roleRepository;

    //@Autowired
    //private final PasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository, RoleRepository roleRepository/*, PasswordEncoder passwordEncoder*/) {
        this.ownerRepository = ownerRepository;
        //this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Obtiene todos los propietarios registrados en el sistema.
     *
     * @return Lista de todos los propietarios
     */
    public List<OwnerEntity> getAll() {
        return this.ownerRepository.findAll();
    }

    /**
     * Obtiene todos los propietarios activos en el sistema.
     *
     * @return Lista de propietarios con estado activo
     */
    public List<OwnerEntity> getOwners() {
        return this.ownerRepository.findAllByEstadoIsTrue();
    }

    /**
     * Busca un propietario por su ID.
     *
     * @param idOwner ID del propietario a buscar
     * @return Propietario encontrado o null si no existe
     */
    public OwnerEntity getById(int idOwner){
        return this.ownerRepository.findById(idOwner).orElse(null);
    }

    /*public OwnerEntity saveOwner(OwnerDTO owner){
        Set<RoleEntity> roles = owner.getRoles().stream()
                .map(role -> RoleEntity.builder().name(ERole.valueOf(role))
                        .build()).collect(Collectors.toSet());

        OwnerEntity.OwnerEntityBuilder builder = OwnerEntity.builder()
                .apellido(owner.getApellido())
                .contacto(owner.getContacto())
                .correo(owner.getCorreo())
                .username(owner.getUsername())
                .estado(true)
                .nombre(owner.getNombre())
                //.password(passwordEncoder.encode(owner.getPassword()))
                .password(owner.getPassword())
                .roles(roles);
        if (owner.getIdOwner() != null && !owner.getIdOwner().describeConstable().isEmpty()) {
            builder.idOwner(owner.getIdOwner());
        }
        OwnerEntity ownerEntity = builder.build();
        return this.ownerRepository.save(ownerEntity);
    }*/

    /*public OwnerEntity saveOwner(OwnerDTO owner) {
        RoleEntity role = null;

        if (owner.getRoles() != null && !owner.getRoles().isEmpty()) {
            role = new RoleEntity();
            role.setName(ERole.valueOf(owner.getRoles()));
        }

        OwnerEntity.OwnerEntityBuilder builder = OwnerEntity.builder()
                .apellido(owner.getApellido())
                .contacto(owner.getContacto())
                .correo(owner.getCorreo())
                .username(owner.getUsername())
                .estado(true)
                .nombre(owner.getNombre())
                //.password(passwordEncoder.encode(owner.getPassword()))
                .password(owner.getPassword()) // Assuming password encoding is handled elsewhere
                .role(role); // Set the single role

        if (owner.getIdOwner() != null && !owner.getIdOwner().describeConstable().isEmpty()) {
            builder.idOwner(owner.getIdOwner());
        }
        OwnerEntity ownerEntity = builder.build();
        return this.ownerRepository.save(ownerEntity);
    }*/

    /**
     * Guarda o actualiza un propietario en el sistema.
     * Si el propietario ya existe, actualiza sus datos manteniendo su estado actual.
     * Si es nuevo, lo crea con estado activo.
     *
     * @param owner DTO con los datos del propietario
     * @return Propietario guardado o actualizado
     */
    public OwnerEntity saveOwner(OwnerDTO owner) {
        RoleEntity role = null;
        boolean estado = true;
        if (owner.getRoleId() != null) {
            Optional<RoleEntity> optionalRole = this.roleRepository.findById(owner.getRoleId());
            if (optionalRole.isPresent()) {
                role = optionalRole.get();
            }
        }
        if ((owner.getIdOwner() != null) && (exists(owner.getIdOwner()))) {
            Optional<OwnerEntity> exist = this.ownerRepository.findById(owner.getIdOwner());
            estado = exist.get().getEstado();
        }

        OwnerEntity.OwnerEntityBuilder builder = OwnerEntity.builder()
                .apellido(owner.getApellido())
                .contacto(owner.getContacto())
                .correo(owner.getCorreo())
                .username(owner.getUsername())
                .estado(estado)
                .nombre(owner.getNombre())
                .password(owner.getPassword())
                .role(role);

        if (owner.getIdOwner() != null && !owner.getIdOwner().describeConstable().isEmpty()) {
            builder.idOwner(owner.getIdOwner());
        }
        OwnerEntity ownerEntity = builder.build();
        return this.ownerRepository.save(ownerEntity);
    }

    /**
     * Verifica si existe un propietario con el ID especificado.
     *
     * @param idOwner ID del propietario a verificar
     * @return true si existe, false en caso contrario
     */
    public Boolean exists(int idOwner){
        return this.ownerRepository.existsById(idOwner);
    }

    /**
     * Marca un propietario como inactivo en el sistema.
     * Esta operación es lógica, no elimina físicamente el registro.
     *
     * @param idOwner ID del propietario a marcar como inactivo
     */
    public void deleteOwner(int idOwner){
        this.ownerRepository.updateEstado(idOwner);
    }
}
