package com.voongc.repositories;
import com.voongc.service.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(String typeName);
    @Query(value = "SELECT * FROM Type ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Type> findRandomType();
    void deleteByName(String name);
}

