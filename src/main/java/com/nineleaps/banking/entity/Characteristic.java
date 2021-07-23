package com.nineleaps.banking.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characteristic", schema = "bank")
@Getter
@Setter
public class Characteristic {

    @Id
    @GeneratedValue(generator = "characteristic_gen")
    @SequenceGenerator(
            name = "characteristic_gen",
            sequenceName = "characteristic_seq",
            schema = "bank",
            allocationSize = 1,
            initialValue = 1)
    private Long id;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Characteristic)) return false;
        return id != null && id.equals(((Characteristic) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
