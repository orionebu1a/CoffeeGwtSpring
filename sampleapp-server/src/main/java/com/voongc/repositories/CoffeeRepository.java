package com.voongc.repositories;

import com.voongc.service.entities.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
