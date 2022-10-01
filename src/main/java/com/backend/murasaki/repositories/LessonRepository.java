package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Lesson;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    Optional<Lesson> findById(Integer id);

    List<Lesson> findAll();

}
