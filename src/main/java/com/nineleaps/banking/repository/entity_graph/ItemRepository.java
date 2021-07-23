package com.nineleaps.banking.repository.entity_graph;

import com.nineleaps.banking.entity.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @EntityGraph(attributePaths = {"characteristics"})
    Item findByName(String name);
}
