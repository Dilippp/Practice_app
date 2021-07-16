package com.nineleaps.banking.practice.jpa.inheritance.mapped_super_class;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FourWheeler_Mapped_Super_Class extends Vehicle_Mapped_Super_Class {

    // no id as inheriting from Vehicle

    // specific to FourWheeler class
    private String steeringWheel;

    // rest of the field come from Vehicle
}
