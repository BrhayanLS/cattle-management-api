package com.adgan.service;

import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.persitence.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<OwnerEntity> getAll() {
        return this.ownerRepository.findAll();
    }
}
