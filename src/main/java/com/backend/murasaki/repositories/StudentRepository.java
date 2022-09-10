package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Student;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Optional<Student> findById(Integer id);

    List<Student> findAll();

}
