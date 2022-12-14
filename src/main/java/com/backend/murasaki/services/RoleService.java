package com.backend.murasaki.services;

import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Role;
import com.backend.murasaki.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role create(String name){
        Role newRole = new Role(name);
        return this.roleRepository.save(newRole);
    }

    @Transactional(readOnly = true)
    public Role getRole(String name){
        return this.roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("The requested role was not found"));
    }

}
