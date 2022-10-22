package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Teacher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer> {

    //@EntityGraph(attributePaths = {"teacherAssigned"}) Perfecto para el error handler
    Optional<Teacher> findById(Integer id);

    List<Teacher> findAll();

    Optional<Teacher> findByName(String teacherName);

    Page<Teacher> findByNameLike(Pageable pageable, String text);

}
