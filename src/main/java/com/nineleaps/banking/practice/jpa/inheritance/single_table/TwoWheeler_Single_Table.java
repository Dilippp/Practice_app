package com.nineleaps.banking.practice.jpa.inheritance.single_table;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue(value = "Bike")
public class TwoWheeler_Single_Table extends Vehicle_Single_Table {

    // no id as inheriting from Vehicle

    // specific to TwoWheeler class
    private String steeringHandle;

    // rest of the field come from Vehicle
}
