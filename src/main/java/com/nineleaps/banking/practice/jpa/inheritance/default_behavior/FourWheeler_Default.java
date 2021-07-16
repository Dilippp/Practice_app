package com.nineleaps.banking.practice.jpa.inheritance.default_behavior;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FourWheeler_Default extends Vehicle_Default {

    // no id as inheriting from Vehicle

    // specific to FourWheeler class
    private String steeringWheel;

    // rest of the field come from Vehicle
}
