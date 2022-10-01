package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    @EntityGraph(attributePaths = {"teacherAssigned", "homeWorks"})
    Optional<Student> findById(Integer id);

    @EntityGraph(attributePaths = {"teacherAssigned"})
    List<Student> findAll();

    List<Student> findByJlptLevel(int jlptLevel);

    List<Student> findByTeacherAssigned(Teacher teacher);

}
