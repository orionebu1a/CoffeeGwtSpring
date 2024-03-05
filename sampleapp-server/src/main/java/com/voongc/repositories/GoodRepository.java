package com.voongc.repositories;

import com.voongc.service.entities.Good;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodRepository extends CrudRepository<Good, Long> {
    Optional<Good> findByName(String goodName);
    void deleteByName(String name);
}

