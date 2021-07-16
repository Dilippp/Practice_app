package com.nineleaps.banking.practice.jpa.inheritance.table_per_class;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class TwoWheeler_Table_Per_Class extends Vehicle_Table_Per_Class {

    // no id as inheriting from Vehicle

    // specific to TwoWheeler class
    private String steeringHandle;

    // rest of the field come from Vehicle
}
