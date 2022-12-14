package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Lesson;
import com.backend.murasaki.models.Link;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {

    Optional<Link> findById(Integer id);

    List<Link> findAll();

}
