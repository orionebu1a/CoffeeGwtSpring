package com.voongc.repositories;

import com.voongc.domain.Coffee;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
