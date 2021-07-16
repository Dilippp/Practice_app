package com.nineleaps.banking.practice.jpa.inheritance.default_behavior;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class TwoWheeler_Default extends Vehicle_Default {

    // no id as inheriting from Vehicle

    // specific to TwoWheeler class
    private String steeringHandle;

    // rest of the field come from Vehicle
}
