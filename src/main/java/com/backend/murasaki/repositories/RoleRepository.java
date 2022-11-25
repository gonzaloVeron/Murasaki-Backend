package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Configuration
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
