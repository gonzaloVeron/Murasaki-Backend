package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    @EntityGraph(attributePaths = {"teacherAssigned", "lessons"})
    Optional<Student> findById(Integer id);

    @EntityGraph(attributePaths = {"teacherAssigned"})
    List<Student> findAll();

    @EntityGraph(attributePaths = {"teacherAssigned"})
    Page<Student> findByJlptLevel(Pageable pageable, int jlptLevel);

    @EntityGraph(attributePaths = {"teacherAssigned"})
    Page<Student> findByJlptLevelAndTeacherAssigned(Pageable pageable, int jlptLevel, Teacher teacher);

    @EntityGraph(attributePaths = {"teacherAssigned"})
    Page<Student> findByNameLikeAndTeacherAssigned(Pageable pageable, String name, Teacher teacher);

    @EntityGraph(attributePaths = {"teacherAssigned"})
    Page<Student> findByNameLike(Pageable pageable, String name);

    @EntityGraph(attributePaths = {"teacherAssigned"})
    List<Student> findByIdIn(List<Integer> ids);

}
