package com.voongc.repositories;
import com.voongc.entities.Type;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends CrudRepository<Type, Long> {
    Optional<Type> findByName(String typeName);
    //@Query(value = "SELECT * FROM Type ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    //Optional<Type> findRandomType();
    void deleteByName(String name);
}

