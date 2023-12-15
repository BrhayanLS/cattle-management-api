package com.adgan.service;

import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.persitence.repository.OwnerRepository;
import com.adgan.service.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<OwnerEntity> getAll() {
        return this.ownerRepository.findAll();
    }

    public OwnerEntity getById(int idOwner){
        return this.ownerRepository.findById(idOwner).orElse(null);
    }

    public OwnerEntity saveOwner(OwnerEntity owner){
        String pass = passwordEncoder.encode(owner.getPassword());
        owner.setPassword(pass);
        return this.ownerRepository.save(owner);
    }

    public Boolean exists(int idOwner){
        return this.ownerRepository.existsById(idOwner);
    }

    public void deleteOwner(int idOwner){
        this.ownerRepository.deleteById(idOwner);
    }
}
