package com.nineleaps.banking.practice.jpa.inheritance.joined_table;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class TwoWheeler_Joined_Table extends Vehicle_Joined_Table {

    // no id as inheriting from Vehicle

    // specific to TwoWheeler class
    private String steeringHandle;

    // rest of the field come from Vehicle
}
