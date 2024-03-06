package com.voongc.repositories;
import com.voongc.entities.Cup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupRepository extends CrudRepository<Cup, Long> {
    Optional<Cup> findByValue(float value);
    Optional<Cup> findFirstByBalanceGreaterThan(int quantity);
    void deleteByValue(float value);
}
