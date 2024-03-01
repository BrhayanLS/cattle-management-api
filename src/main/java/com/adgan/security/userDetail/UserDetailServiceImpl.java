package com.adgan.security.userDetail;

import com.adgan.persitence.entity.OwnerEntity;
import com.adgan.persitence.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OwnerEntity ownerEntity = ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario "+ username + " no existe."));

        Collection<? extends GrantedAuthority> authorities = ownerEntity.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(ownerEntity.getUsername(), ownerEntity.getPassword(),
                true,true,true,true,authorities);
    }
}
