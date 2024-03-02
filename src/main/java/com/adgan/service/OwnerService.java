package com.adgan.service;

import com.adgan.persitence.entity.ERole;
import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.persitence.entity.RoleEntity;
import com.adgan.persitence.repository.OwnerRepository;
import com.adgan.service.dto.OwnerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    private final OwnerRepository ownerRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<OwnerEntity> getAll() {
        return this.ownerRepository.findAll();
    }

    public List<OwnerEntity> getOwners() {
        return this.ownerRepository.findAllByEstadoIsTrue();
    }

    public OwnerEntity getById(int idOwner){
        return this.ownerRepository.findById(idOwner).orElse(null);
    }

    public OwnerEntity saveOwner(OwnerDTO owner){
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
                .password(passwordEncoder.encode(owner.getPassword()))
                .roles(roles);
        if (owner.getIdOwner() != null && !owner.getIdOwner().describeConstable().isEmpty()) {
            builder.idOwner(owner.getIdOwner());
        }
        OwnerEntity ownerEntity = builder.build();
        return this.ownerRepository.save(ownerEntity);
    }

    public Boolean exists(int idOwner){
        return this.ownerRepository.existsById(idOwner);
    }

    public void deleteOwner(int idOwner){
        this.ownerRepository.updateEstado(idOwner);
    }
}
