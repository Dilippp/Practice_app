package com.nineleaps.banking.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item", schema = "bank")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(generator = "item_gen")
    @SequenceGenerator(
            name = "item_gen",
            sequenceName = "item_seq",
            schema = "bank",
            allocationSize = 1,
            initialValue = 1)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Characteristic> characteristics = new ArrayList<>();

    // utility method
    public void addCharacteristic(Characteristic characteristic) {
        characteristics.add(characteristic);
        characteristic.setItem(this);
    }

    public void removeCharacteristic(Characteristic characteristic) {
        characteristics.remove(characteristic);
        characteristic.setItem(null);
    }
}
