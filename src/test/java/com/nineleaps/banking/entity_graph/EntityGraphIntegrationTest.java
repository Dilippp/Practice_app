package com.nineleaps.banking.entity_graph;

import static org.assertj.core.api.Assertions.assertThat;

import com.nineleaps.banking.entity.Characteristic;
import com.nineleaps.banking.entity.Item;
import com.nineleaps.banking.repository.entity_graph.CharacteristicsRepository;
import com.nineleaps.banking.repository.entity_graph.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @DataJpaTest
@RunWith(SpringRunner.class)
// @Sql(scripts = "/sql/entity-graph-data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntityGraphIntegrationTest {

    @Autowired private ItemRepository itemRepository;
    @Autowired private CharacteristicsRepository characteristicsRepository;

    @Test
    public void givenEntityGraph_whenCalled_shouldReturnDefinedFields() {
        Item item = itemRepository.findByName("Table");
        assertThat(item.getId()).isEqualTo(1L);
    }

    @Test
    public void givenAdhocEntityGraph_whenCalled_shouldReturnDefinedFields() {
        Characteristic characteristic = characteristicsRepository.findByType("Rigid");
        assertThat(characteristic.getId()).isEqualTo(1L);
    }
}
