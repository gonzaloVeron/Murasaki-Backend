package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Teacher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

    Optional<Teacher> findById(Integer id);

    List<Teacher> findAll();

}
