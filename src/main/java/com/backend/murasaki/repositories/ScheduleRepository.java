package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Schedule;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

    Optional<Schedule> findById(Integer id);

    List<Schedule> findAll();

}
