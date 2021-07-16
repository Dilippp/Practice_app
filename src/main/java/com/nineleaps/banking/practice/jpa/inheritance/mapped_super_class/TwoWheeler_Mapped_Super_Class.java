package com.nineleaps.banking.practice.jpa.inheritance.mapped_super_class;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class TwoWheeler_Mapped_Super_Class extends Vehicle_Mapped_Super_Class {

    // no id as inheriting from Vehicle

    // specific to TwoWheeler class
    private String steeringHandle;

    // rest of the field come from Vehicle
}
