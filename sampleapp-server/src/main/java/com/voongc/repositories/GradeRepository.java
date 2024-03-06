package com.voongc.repositories;
import com.voongc.entities.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
    Optional<Grade> findByName(String gradeName);
    Optional<Grade> findFirstByBalanceGreaterThan(int quantity);
    void deleteByName(String name);
}
