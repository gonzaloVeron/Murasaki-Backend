package com.backend.murasaki.repositories;

import com.backend.murasaki.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    Optional<User> findById(Integer id);

    @EntityGraph(attributePaths = {"teacher"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"teacher"})
    Optional<User> findByTeacher_id(Integer id);

    List<User> findAll();

}
