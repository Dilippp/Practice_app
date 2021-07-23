package com.nineleaps.banking.repository.entity_graph;

import com.nineleaps.banking.entity.Characteristic;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicsRepository extends JpaRepository<Characteristic, Long> {

    @EntityGraph(attributePaths = {"item"})
    Characteristic findByType(String type);
}
