package com.backend.murasaki.repositories;

import com.backend.murasaki.models.HomeWork;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface HomeWorkRepository extends CrudRepository<HomeWork, Integer> {

    Optional<HomeWork> findById(Integer id);

    List<HomeWork> findAll();

}
