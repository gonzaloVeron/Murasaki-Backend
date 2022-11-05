package com.backend.murasaki.repositories;

import com.backend.murasaki.models.Interest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InterestRespository extends CrudRepository<Interest, Integer> {

    Optional<Interest> findById(Integer id);

    List<Interest> findAll();

    Page<Interest> findByNameLike(Pageable pageable, String text);

}
